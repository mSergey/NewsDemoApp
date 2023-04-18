package com.gmail.zajcevserg.feature_news_details.presentation

import com.gmail.zajcevserg.feature_news_details.domain.DetailsUiModel

private const val STUB = "Stub!"
data class DetailsUiState(
    val detailsUiModel: DetailsUiModel,
    val isInFavorites: Boolean,
    val isInitial: Boolean
) {
    companion object {
        fun createEmptyState(): DetailsUiState {
            return DetailsUiState(
                detailsUiModel = DetailsUiModel(
                    id = 0,
                    source = STUB,
                    author = STUB,
                    title = STUB,
                    urlToImage = STUB,
                    publishedAt = STUB,
                    url = STUB,
                    content = STUB,
                    description = STUB,
                ),
                isInitial = true,
                isInFavorites = false
            )
        }
    }
}
