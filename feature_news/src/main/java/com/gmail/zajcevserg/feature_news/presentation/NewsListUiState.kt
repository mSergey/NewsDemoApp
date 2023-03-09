package com.gmail.zajcevserg.feature_news.presentation

import com.gmail.zajcevserg.feature_news.domain.ArticleUiModel

data class NewsListUiState(
    val articles: List<ArticleUiModel>,
    val noInternetConnection: Boolean,
    val serverDoNotResponse: Boolean,
    val isInitial: Boolean
) {
    companion object {
        fun createEmptyState(): NewsListUiState {
            return NewsListUiState(
                articles = emptyList(),
                noInternetConnection = false,
                serverDoNotResponse = false,
                isInitial = true
            )
        }
    }
}
