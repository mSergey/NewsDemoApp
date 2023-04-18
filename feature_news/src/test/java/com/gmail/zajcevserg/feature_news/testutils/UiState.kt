package com.gmail.zajcevserg.feature_news.testutils

import com.gmail.zajcevserg.feature_news.presentation.NewsListUiState

class UiState {
    companion object {
        fun getUiStateWithNewsData(): NewsListUiState {
            return NewsListUiState(
                articles = UiResponse.getNewsUiResponseNewsData().articles,
                noInternetConnection = false,
                serverDoNotResponse = false,
                isInitial = false
            )
        }
        fun getUiStateWithNetworkError(): NewsListUiState {
            return NewsListUiState(
                articles = listOf(),
                noInternetConnection = true,
                serverDoNotResponse = false,
                isInitial = false
            )
        }

        fun getUiStateWithServerError(): NewsListUiState {
            return NewsListUiState(
                articles = listOf(),
                noInternetConnection = false,
                serverDoNotResponse = true,
                isInitial = false
            )
        }
    }
}