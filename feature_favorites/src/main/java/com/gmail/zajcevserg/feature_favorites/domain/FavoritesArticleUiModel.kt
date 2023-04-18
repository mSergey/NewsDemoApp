package com.gmail.zajcevserg.feature_favorites.domain

data class FavoritesArticleUiModel(
    val id: Long,
    val sourceName: String,
    val title: String,
    val urlToImage: String,
    val url: String
)