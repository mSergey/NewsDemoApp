package com.gmail.zajcevserg.feature_news.testutils

import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import kotlinx.coroutines.flow.flowOf

class DatabaseEntity {
    companion object {
        fun getNewsEntitiesFlow() = flowOf(
            listOf(
                NewsEntity(
                    id = 9,
                    sourceName = "BBC",
                    author = "John Doe",
                    title = "Hello",
                    description = "Hello people",
                    url = "ww.url.com",
                    urlToImage = "www.image.com",
                    publishedAt = "2023, June 12",
                    content = "Stub content"
                )
            )
        )
    }
}