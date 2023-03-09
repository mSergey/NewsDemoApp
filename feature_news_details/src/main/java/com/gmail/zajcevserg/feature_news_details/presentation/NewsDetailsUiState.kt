package com.gmail.zajcevserg.feature_news_details.presentation

import com.gmail.zajcevserg.feature_news_details.domain.NewsDetailsUiModel

private const val STUB = "Stub!"
data class NewsDetailsUiState(
    val detailsUiModel: NewsDetailsUiModel,
    val isInitial: Boolean
) {
    companion object {
        fun createEmptyState(): NewsDetailsUiState {
            return NewsDetailsUiState(
                detailsUiModel = NewsDetailsUiModel(
                    source = STUB,
                    author = STUB,
                    title = STUB,
                    urlToImage = STUB,
                    publishedAt = STUB,
                    content = STUB
                ),
                isInitial = true
            )
        }
    }
}
