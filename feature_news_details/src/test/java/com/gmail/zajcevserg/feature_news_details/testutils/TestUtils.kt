package com.gmail.zajcevserg.feature_news_details.testutils

import com.gmail.zajcevserg.core_api.datasource.local.FavoritesArticleEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import com.gmail.zajcevserg.feature_news_details.domain.DetailsUiModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class TestUtils {

    companion object {

        fun getNewsEntity(): NewsEntity {
            return NewsEntity(
                id = 0,
                sourceName = "BBC",
                author = "John Doe",
                title = "Hello",
                description = "Hello people",
                url = "www.url.com",
                urlToImage = "www.image.com",
                publishedAt = "2023, June 12",
                content = "Stub content"
            )
        }
        fun getDetailsUiModel(): DetailsUiModel {
            return DetailsUiModel(
                id = 0,
                source = "BBC",
                author = "John Doe",
                title = "Hello",
                description = "Hello people",
                url = "www.url.com",
                urlToImage = "www.image.com",
                publishedAt = "2023, June 12",
                content = "Stub content"
            )
        }
        fun getFavoritesArticleEntity(): FavoritesArticleEntity {
            return FavoritesArticleEntity(
                id = 0,
                sourceName = "BBC",
                title = "Hello",
                urlToImage = "www.image.com",
                url = "www.url.com"
            )
        }

        fun getArticleEntitiesFlowWithFavoritesItem() = flow {
            val entities = listOf(
                FavoritesArticleEntity(
                    id = 0,
                    sourceName = "BBC",
                    title = "No favorites title",
                    urlToImage = "www.image.com",
                    url = "www.no.favorites.url.com"
                ),
                FavoritesArticleEntity(
                    id = 1,
                    sourceName = "BBC",
                    title = "Hello",
                    urlToImage = "www.image.com",
                    url = "www.url.com"
                ),
                FavoritesArticleEntity(
                    id = 2,
                    sourceName = "BBC",
                    title = "No favorites title",
                    urlToImage = "www.image.com",
                    url = "www.no.favorites.url.com"
                )
            )
            emit(entities)
        }

        fun getArticleEntitiesFlowWithoutFavoritesItem() = flow {
            val entities = listOf(
                FavoritesArticleEntity(
                    id = 0,
                    sourceName = "BBC",
                    title = "No favorites title",
                    urlToImage = "www.image.com",
                    url = "www.no.favorites.url.com"
                ),
                FavoritesArticleEntity(
                    id = 1,
                    sourceName = "BBC",
                    title = "No favorites title",
                    urlToImage = "www.image.com",
                    url = "www.no.favorites.url.com"
                ),
                FavoritesArticleEntity(
                    id = 2,
                    sourceName = "BBC",
                    title = "No favorites title",
                    urlToImage = "www.image.com",
                    url = "www.no.favorites.url.com"
                )
            )
            emit(entities)
        }

    }
}