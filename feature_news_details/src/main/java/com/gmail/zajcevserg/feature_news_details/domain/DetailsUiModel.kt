package com.gmail.zajcevserg.feature_news_details.domain

data class DetailsUiModel(
    val id: Long,
    val source: String,
    val author: String,
    val description: String,
    val title: String,
    val urlToImage: String,
    val url: String,
    val publishedAt: String,
    val content: String
)
