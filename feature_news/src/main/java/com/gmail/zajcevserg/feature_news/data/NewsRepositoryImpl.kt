package com.gmail.zajcevserg.feature_news.data

import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource
import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkResult
import com.gmail.zajcevserg.feature_news.domain.ArticleUiModel
import com.gmail.zajcevserg.feature_news.domain.NewsUiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) : NewsRepository {

    override fun observeNewsResponse() = channelFlow<NewsUiResponse> {
        launch(context = Dispatchers.IO) {
            localDataSource.getNews()
                .map { it.fromDatabaseToToUi() }
                .distinctUntilChanged()
                .collect { send(it) }
        }

        launch(context = Dispatchers.IO) {
            when (val newsNetResult = remoteDataSource.fetchNews()) {
                is NewsNetworkResult.Success -> {
                    localDataSource.updateNewsLocal(
                        newsNetResult.fromNetworkToDatabase()
                    )
                }
                is NewsNetworkResult.Failure -> {
                    if (newsNetResult.isNetworkError) {
                        send(NewsUiResponse.NetworkError)
                    } else {
                        send(
                            NewsUiResponse.ServerError(
                                message = newsNetResult.errorData?.message ?: "Unknown server error"
                            )
                        )
                    }
                }
            }
        }
    }

    private fun NewsNetworkResult.Success.fromNetworkToDatabase(): List<NewsEntity> {
        return data.articles.map {
            NewsEntity(
                id = 0,
                sourceName = it.source?.name ?: "No data",
                author = it.author ?: "No data",
                title = it.title ?: "No data",
                description = it.description  ?: "No data",
                url = it.url ?: "No data",
                urlToImage = it.urlToImage  ?: "No data",
                publishedAt = it.publishedAt  ?: "No data",
                content = it.content ?: "No data"
            )
        }
    }

    /*private fun NewsResult.Success.fromNetworkToToUi(): NewsUiModel.NewsData {
        val articles = mutableListOf<ArticleUiModel>()
        data.articles.forEach {
            val article = ArticleUiModel(
                id = it.id,
                sourceName = it.source.name,
                author = it.author ?: "No author",
                title = it.title,
                description = it.description,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt
            )
            articles.add(article)
        }
        return NewsUiModel.NewsData(articles = articles)
    }*/

    private fun List<NewsEntity>.fromDatabaseToToUi(): NewsUiResponse.NewsData {
        val cacheArticles = map {
            ArticleUiModel(
                id = it.id,
                sourceName = it.sourceName,
                author = it.author,
                title = it.title,
                description = it.description,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt
            )
        }
        return NewsUiResponse.NewsData(
            articles = cacheArticles
        )
    }
}