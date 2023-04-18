package com.gmail.zajcevserg.feature_favorites.data

import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.feature_favorites.domain.FavoritesArticleUiModel
import com.gmail.zajcevserg.feature_favorites.domain.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val localDataSource: NewsLocalDataSource
) : FavoritesRepository {
    override suspend fun getFavoritesArticles(): Flow<List<FavoritesArticleUiModel>> {
        return localDataSource.getFavoritesArticles()
            .map { articleEntities ->
                articleEntities.map {
                    FavoritesArticleUiModel(
                        id = it.id,
                        sourceName = it.sourceName,
                        title = it.title,
                        urlToImage = it.urlToImage,
                        url = it.url
                    )
                }
            }
    }

    override suspend fun deleteArticleById(id: Long) {
        localDataSource.deleteFavoritesArticleById(id)
    }
}