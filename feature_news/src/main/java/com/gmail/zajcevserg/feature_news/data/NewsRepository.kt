package com.gmail.zajcevserg.feature_news.data

import com.gmail.zajcevserg.feature_news.domain.NewsUiResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun observeNewsResponse(): Flow<NewsUiResponse>
}