package com.gmail.zajcevserg.feature_news_details.data

import com.gmail.zajcevserg.core_api.datasource.local.FavoritesArticleEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.feature_news_details.domain.DetailsUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsDetailsRepositoryImpl @Inject constructor(
    private val localDataSource: NewsLocalDataSource
) : NewsDetailsRepository {
    override suspend fun fetchNewsDetails(id: Long): DetailsUiModel {
        return withContext(context = Dispatchers.IO) {
            localDataSource.getNewsDetailsById(id).fromDatabaseToUi()
        }
    }

    override suspend fun saveToFavorites(articleUiModel: DetailsUiModel) {
        localDataSource.addArticleToFavorites(
            entity = articleUiModel.fromUiToDatabase()
        )
    }

    override suspend fun removeFavorites(id: Long) {
        localDataSource.deleteFavoritesArticleById(id)
    }

    override suspend fun isArticleInFavorites(details: DetailsUiModel): Flow<Boolean> {
        return localDataSource.getFavoritesArticles().map { favoritesEntities ->
            favoritesEntities.any {
                it.title == details.title &&
                        it.url == details.url
            }
        }
    }

    private fun NewsEntity.fromDatabaseToUi(): DetailsUiModel {
        return DetailsUiModel(
            id = this.id,
            source = this.sourceName,
            author = this.author,
            title = this.title,
            urlToImage = this.urlToImage,
            publishedAt = this.publishedAt,
            description = this.description,
            content = this.content,
            url = this.url
        )
    }
    private fun DetailsUiModel.fromUiToDatabase(): FavoritesArticleEntity {
        return FavoritesArticleEntity(
            id = this.id,
            sourceName = this.source,
            title = this.title,
            urlToImage = this.urlToImage,
            url = this.url

        )
    }
}