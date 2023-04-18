package com.gmail.zajcevserg.feature_news_details.data

import com.gmail.zajcevserg.feature_news_details.domain.DetailsUiModel
import kotlinx.coroutines.flow.Flow

interface NewsDetailsRepository {
    suspend fun fetchNewsDetails(id: Long): DetailsUiModel
    suspend fun saveToFavorites(articleUiModel: DetailsUiModel)
    suspend fun removeFavorites(id: Long)
    suspend fun isArticleInFavorites(details: DetailsUiModel): Flow<Boolean>
}