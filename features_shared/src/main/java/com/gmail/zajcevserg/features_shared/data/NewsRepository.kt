package com.gmail.zajcevserg.features_shared.data

import com.gmail.zajcevserg.features_shared.domain.NewsUiModel

interface NewsRepository {
    suspend fun fetchNews(): List<NewsUiModel>
}