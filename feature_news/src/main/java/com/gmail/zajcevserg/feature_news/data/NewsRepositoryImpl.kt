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
        println("observeNewsResponse()")
        launch(context = Dispatchers.IO) {
            println("localDataSource.getNews()")
            localDataSource.getNews()
                .map { it.fromDatabaseToToUi() }
                .distinctUntilChanged()
                .collect { send(it) }
        }

        launch(context = Dispatchers.IO) {
            println("remoteDataSource.fetchNews()")
            when (val newsNetResult = remoteDataSource.fetchNews()) {
                is NewsNetworkResult.Success -> {
                    println("is NewsNetworkResult.Success")
                    localDataSource.updateNewsLocal(
                        newsNetResult.fromNetworkToDatabase()
                    )
                }

                is NewsNetworkResult.Failure -> {
                    println("is NewsNetworkResult.Failure")
                    if (newsNetResult.isNetworkError) {
                        send(NewsUiResponse.NetworkError)
                    } else {
                        send(
                            NewsUiResponse.ServerError(
                                message = newsNetResult.errorData?.message ?: "Server error"
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
                sourceName = it.source?.name ?: "No source name",
                author = it.author ?: "No author",
                title = it.title ?: "No title",
                description = it.description  ?: "No data",
                url = it.url ?: "No data",
                urlToImage = it.urlToImage  ?: "No data",
                publishedAt = it.publishedAt  ?: "No data",
                content = it.content ?: "No data"
            )
        }
    }

    private fun List<NewsEntity>.fromDatabaseToToUi(): NewsUiResponse.NewsData {
        val cacheArticles = map {
            ArticleUiModel(
                id = it.id,
                sourceName = it.sourceName,
                title = it.title,
                urlToImage = it.urlToImage
            )
        }
        return NewsUiResponse.NewsData(
            articles = cacheArticles
        )
    }
}