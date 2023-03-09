package com.gmail.zajcevserg.feature_news_details.domain

data class NewsDetailsUiModel(
    val source: String,
    val author: String,
    val title: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
