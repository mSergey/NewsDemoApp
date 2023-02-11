package com.gmail.zajcevserg.features_shared.domain

data class NewsUiModel(
    val sourceName: String,
    val author: String,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String
)
