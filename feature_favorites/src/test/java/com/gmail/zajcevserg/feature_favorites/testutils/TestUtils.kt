package com.gmail.zajcevserg.feature_favorites.testutils

import com.gmail.zajcevserg.core_api.datasource.local.FavoritesArticleEntity
import com.gmail.zajcevserg.feature_favorites.domain.FavoritesArticleUiModel
import com.gmail.zajcevserg.feature_favorites.presentation.FavoritesUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TestUtils {
    companion object {
        fun getFavoritesArticleUiModels(): List<FavoritesArticleUiModel> {
            return listOf(
                FavoritesArticleUiModel(
                    id = 0,
                    sourceName = "BBC",
                    title = "Hello",
                    urlToImage = "www.image.ru",
                    url = "www.url.ru"
                ),
                FavoritesArticleUiModel(
                    id = 1,
                    sourceName = "BBC",
                    title = "Android",
                    urlToImage = "www.image.ru",
                    url = "www.url.ru"
                ),
                FavoritesArticleUiModel(
                    id = 2,
                    sourceName = "BBC",
                    title = "Hello",
                    urlToImage = "www.image.ru",
                    url = "www.url.ru"
                )
            )
        }

        fun getFavoritesArticleUiModelsFlow(): Flow<List<FavoritesArticleUiModel>> {
            return flowOf(
                listOf(
                    FavoritesArticleUiModel(
                        id = 0,
                        sourceName = "BBC",
                        title = "Hello",
                        urlToImage = "www.image.ru",
                        url = "www.url.ru"
                    ),
                    FavoritesArticleUiModel(
                        id = 1,
                        sourceName = "BBC",
                        title = "Android",
                        urlToImage = "www.image.ru",
                        url = "www.url.ru"
                    ),
                    FavoritesArticleUiModel(
                        id = 2,
                        sourceName = "BBC",
                        title = "Hello",
                        urlToImage = "www.image.ru",
                        url = "www.url.ru"
                    )
                )
            )
        }

        fun getUiStateWithData(): FavoritesUiState {
            return FavoritesUiState(
                favoritesArticles = getFavoritesArticleUiModels(),
                isInitial = false
            )
        }

        fun getFavoritesArticleEntitiesFlow() = flowOf(
            listOf(
                FavoritesArticleEntity(
                    id = 0,
                    sourceName = "BBC",
                    title = "Hello",
                    urlToImage = "www.image.ru",
                    url = "www.url.ru"
                ),
                FavoritesArticleEntity(
                    id = 1,
                    sourceName = "BBC",
                    title = "Android",
                    urlToImage = "www.image.ru",
                    url = "www.url.ru"
                ),
                FavoritesArticleEntity(
                    id = 2,
                    sourceName = "BBC",
                    title = "Hello",
                    urlToImage = "www.image.ru",
                    url = "www.url.ru"
                )
            )
        )
    }
}