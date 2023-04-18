package com.gmail.zajcevserg.feature_favorites.data

import app.cash.turbine.test
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.feature_favorites.testutils.TestUtils
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FavoritesRepositoryImplTest {

    private val localDataSource: NewsLocalDataSource = mock()
    private val favoritesRepository: FavoritesRepositoryImpl =
        FavoritesRepositoryImpl(localDataSource = localDataSource)

    @After
    fun tearDown() {
        Mockito.clearAllCaches()
    }

    @Test
    fun `should return article ui models list flow`() {
        runBlocking {
            whenever(methodCall = localDataSource.getFavoritesArticles())
                .thenReturn(TestUtils.getFavoritesArticleEntitiesFlow())
            favoritesRepository.getFavoritesArticles().test {
                val expected = TestUtils.getFavoritesArticleUiModels()
                val actual = awaitItem()
                assertEquals(expected, actual)
                awaitComplete()
            }
        }
    }

    @Test
    fun `should invoke delete function`() {
        runBlocking {
            val idArg = 0L
            favoritesRepository.deleteArticleById(id = idArg)
            verify(
                mock = localDataSource,
                mode = times(1)
            ).deleteFavoritesArticleById(id = idArg)
        }
    }
}