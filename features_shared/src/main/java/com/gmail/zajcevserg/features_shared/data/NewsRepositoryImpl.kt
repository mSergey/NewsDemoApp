package com.gmail.zajcevserg.features_shared.data

import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource
import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkDto
import com.gmail.zajcevserg.features_shared.domain.NewsUiModel
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun fetchNews(): List<NewsUiModel> {
        return remoteDataSource.fetchNews().mapToUi()
    }

    private fun NewsNetworkDto.mapToUi(): List<NewsUiModel> {
        val news = mutableListOf<NewsUiModel>()
        articles.forEach {
            val newsItem = NewsUiModel(
                sourceName = it.source.name,
                author = it.author ?: "No author",
                title = it.title,
                description = it.description,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt
            )
            news.add(newsItem)
        }
        return news
    }
}