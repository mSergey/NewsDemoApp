package com.gmail.zajcevserg.feature_favorites.data

import com.gmail.zajcevserg.feature_favorites.domain.FavoritesArticleUiModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun getFavoritesArticles(): Flow<List<FavoritesArticleUiModel>>
    suspend fun deleteArticleById(id: Long)

}
