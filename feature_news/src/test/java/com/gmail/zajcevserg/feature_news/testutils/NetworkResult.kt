package com.gmail.zajcevserg.feature_news.testutils

import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkResult
import com.gmail.zajcevserg.core_api.datasource.remote.dto.success.Article
import com.gmail.zajcevserg.core_api.datasource.remote.dto.success.NewsDto
import com.gmail.zajcevserg.core_api.datasource.remote.dto.success.Source

class NetworkResult {
    companion object {
        fun getNewsNetworkResultSuccess(): NewsNetworkResult.Success {
            return NewsNetworkResult.Success(
                data = NewsDto(
                    status = "OK",
                    totalResults = 50,
                    articles = listOf(
                        Article(
                            source = Source(
                                id = "0",
                                name = "BBC"
                            ),
                            author = "John Freed",
                            title = "Hello",
                            description = "Hello people!",
                            url = "ww.url.com",
                            urlToImage = "www.image.com",
                            publishedAt = "2023, June 14",
                            content = "Stub new content"
                        )
                    )
                )
            )
        }

        fun getNewsNetworkResultFailureNetworkError(): NewsNetworkResult.Failure {
            return NewsNetworkResult.Failure(
                isNetworkError = true,
                errorCode = null,
                errorData = null
            )
        }

        fun getNewsNetworkResultFailureServerError(): NewsNetworkResult.Failure {
            return NewsNetworkResult.Failure(
                isNetworkError = false,
                errorCode = null,
                errorData = null
            )
        }
    }
}