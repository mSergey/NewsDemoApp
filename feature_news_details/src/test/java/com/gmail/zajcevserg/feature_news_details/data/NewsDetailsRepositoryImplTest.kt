package com.gmail.zajcevserg.feature_news_details.data

import app.cash.turbine.test
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.feature_news_details.testutils.TestUtils
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NewsDetailsRepositoryImplTest {

    private val localDataSource: NewsLocalDataSource = mock()
    private val repository: NewsDetailsRepositoryImpl = NewsDetailsRepositoryImpl(
        localDataSource = localDataSource
    )

    @After
    fun tearDown() {
        Mockito.clearAllCaches()
    }

    @Test
    fun `should return news details ui model`() {
        runBlocking {
            val arg = 9L
            whenever(
                methodCall = localDataSource.getNewsDetailsById(arg)
            ).thenReturn(TestUtils.getNewsEntity())
            val actual = repository.fetchNewsDetails(id = arg)
            val expected = TestUtils.getDetailsUiModel()
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should call add to favorites datasource function`() {
        runBlocking {
            repository.saveToFavorites(articleUiModel = TestUtils.getDetailsUiModel())
            verify(
                mock = localDataSource,
                mode = times(1)
            ).addArticleToFavorites(entity = TestUtils.getFavoritesArticleEntity())
        }
    }

    @Test
    fun `should call delete favorites datasource function by id`() {
        runBlocking {
            val idArg = 12L
            repository.removeFavorites(id = idArg)
            verify(
                mock = localDataSource,
                mode = times(1)
            ).deleteFavoritesArticleById(id = idArg)
        }
    }

    @Test
    fun `should emit true when article is in favorites`() {
        runBlocking {
            whenever(methodCall = localDataSource.getFavoritesArticles())
                .thenReturn(TestUtils.getArticleEntitiesFlowWithFavoritesItem())
            repository.isArticleInFavorites(details = TestUtils.getDetailsUiModel())
                .test {
                    assertTrue(awaitItem())
                    awaitComplete()
                }
        }
    }
    @Test
    fun `should emit false when article is out of favorites`() {
        runBlocking {
            whenever(methodCall = localDataSource.getFavoritesArticles())
                .thenReturn(TestUtils.getArticleEntitiesFlowWithoutFavoritesItem())
            repository.isArticleInFavorites(details = TestUtils.getDetailsUiModel())
                .test {
                    assertFalse(awaitItem())
                    awaitComplete()
                }
        }
    }
}