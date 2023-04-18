package com.gmail.zajcevserg.feature_favorites.presentation

sealed class FavoritesAction {
    data class ActionDelete(val id: Long): FavoritesAction()
    object ActionGet : FavoritesAction()
}
