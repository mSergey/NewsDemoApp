package com.gmail.zajcevserg.feature_news_details.data

import com.gmail.zajcevserg.feature_news_details.domain.NewsDetailsUiModel

interface NewsDetailsRepository {
    suspend fun fetchNewsDetails(id: Long): NewsDetailsUiModel
}