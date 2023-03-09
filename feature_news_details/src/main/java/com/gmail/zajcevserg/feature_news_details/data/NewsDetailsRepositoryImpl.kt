package com.gmail.zajcevserg.feature_news_details.data

import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.feature_news_details.domain.NewsDetailsUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsDetailsRepositoryImpl @Inject constructor(
    private val localDataSource: NewsLocalDataSource
) : NewsDetailsRepository {
    override suspend fun fetchNewsDetails(id: Long): NewsDetailsUiModel {
        return withContext(context = Dispatchers.IO) {
            localDataSource.getNewsDetailsById(id).mapToUi()
        }
    }

    private fun NewsEntity.mapToUi(): NewsDetailsUiModel {
        return NewsDetailsUiModel(
            source = this.sourceName,
            author = this.author,
            title = this.title,
            urlToImage = this.urlToImage,
            publishedAt = this.publishedAt,
            content = this.content
        )
    }
}