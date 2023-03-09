package com.gmail.zajcevserg.feature_news.domain


sealed class NewsUiResponse {
    data class NewsData(
        val articles: List<ArticleUiModel>
    ) : NewsUiResponse()

    object NetworkError : NewsUiResponse()
    data class ServerError(
        val message: String
    ) : NewsUiResponse()

}
