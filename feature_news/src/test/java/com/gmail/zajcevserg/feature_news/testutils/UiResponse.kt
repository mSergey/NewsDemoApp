package com.gmail.zajcevserg.feature_news.testutils

import com.gmail.zajcevserg.feature_news.domain.ArticleUiModel
import com.gmail.zajcevserg.feature_news.domain.NewsUiResponse
import kotlinx.coroutines.flow.flow

class UiResponse {
    companion object {

        fun getNewsUiResponseNewsDataFlow() = flow {
            emit(getNewsUiResponseNewsData())
        }

        fun getNewsUiResponseNetworkErrorFlow() = flow {
            emit(getNewsUiResponseNetworkError())
        }

        fun getNewsUiResponseServerErrorFlow() = flow {
            emit(getNewsUiResponseServerError())
        }
        fun getNewsUiResponseNewsData(): NewsUiResponse.NewsData {
            return NewsUiResponse.NewsData(
                articles = listOf(
                    ArticleUiModel(
                        id = 9,
                        sourceName = "BBC",
                        title = "Hello",
                        urlToImage = "www.image.com"
                    )
                )
            )
        }
        fun getNewsUiResponseNetworkError(): NewsUiResponse.NetworkError {
            return NewsUiResponse.NetworkError
        }

        fun getNewsUiResponseServerError(): NewsUiResponse.ServerError {
            return NewsUiResponse.ServerError("Server error")
        }
    }
}