package com.gmail.zajcevserg.feature_favorites.domain

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun getFavoritesArticles(): Flow<List<FavoritesArticleUiModel>>
    suspend fun deleteArticleById(id: Long)

}
