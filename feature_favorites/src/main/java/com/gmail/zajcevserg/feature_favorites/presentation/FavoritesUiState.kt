package com.gmail.zajcevserg.feature_favorites.presentation

import com.gmail.zajcevserg.feature_favorites.domain.FavoritesArticleUiModel
data class FavoritesUiState(
    val favoritesArticles: List<FavoritesArticleUiModel>,
    val isInitial: Boolean
) {
    companion object {
        fun createEmptyState(): FavoritesUiState {
            return FavoritesUiState(
                favoritesArticles = emptyList(),
                isInitial = true
            )
        }
    }
}
