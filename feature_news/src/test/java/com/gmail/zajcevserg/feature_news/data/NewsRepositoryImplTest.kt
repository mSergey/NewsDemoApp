package com.gmail.zajcevserg.feature_news.data

import app.cash.turbine.test
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource
import com.gmail.zajcevserg.feature_news.testutils.DatabaseEntity
import com.gmail.zajcevserg.feature_news.testutils.NetworkResult
import com.gmail.zajcevserg.feature_news.testutils.UiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryImplTest {

    private val localDataSource: NewsLocalDataSource = mock()
    private val remoteDataSource: NewsRemoteDataSource = mock()
    private val newsRepository = NewsRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource
    )

    @Before
    fun setUp() {
        runBlocking {
            whenever(methodCall = localDataSource.getNews())
                .thenReturn(DatabaseEntity.getNewsEntitiesFlow())
        }
    }

    @Test
    fun `should emit twice if network error`() {
        runTest {
            whenever(methodCall = remoteDataSource.fetchNews())
                .thenReturn(NetworkResult.getNewsNetworkResultFailureNetworkError())
            newsRepository.observeNewsResponse().test {
                val firstEmit = awaitItem()
                val secondEmit = awaitItem()
                val condition1 = firstEmit == UiResponse.getNewsUiResponseNetworkError()
                        && secondEmit == UiResponse.getNewsUiResponseNewsData()
                val condition2 = secondEmit == UiResponse.getNewsUiResponseNetworkError()
                        && firstEmit == UiResponse.getNewsUiResponseNewsData()
                assertTrue(condition1 || condition2)
                awaitComplete()
            }
        }
    }

    @Test
    fun `should emit twice if server error`() {
        runTest {
            whenever(methodCall = remoteDataSource.fetchNews())
                .thenReturn(NetworkResult.getNewsNetworkResultFailureServerError())
            newsRepository.observeNewsResponse().test {
                val firstEmit = awaitItem()
                val secondEmit = awaitItem()
                val condition1 = firstEmit == UiResponse.getNewsUiResponseServerError()
                        && secondEmit == UiResponse.getNewsUiResponseNewsData()
                val condition2 = secondEmit == UiResponse.getNewsUiResponseServerError()
                        && firstEmit == UiResponse.getNewsUiResponseNewsData()
                assertTrue(condition1 || condition2)
                awaitComplete()
            }
        }
    }

    @Test
    fun `should emit once if success`() {
        runTest {
            whenever(methodCall = remoteDataSource.fetchNews())
                .thenReturn(NetworkResult.getNewsNetworkResultSuccess())
            newsRepository.observeNewsResponse().test {
                assertTrue(awaitItem() == UiResponse.getNewsUiResponseNewsData())
                awaitComplete()
            }
        }
    }

    @Test
    fun `should call update local db function if success`() {
        runBlocking {
            whenever(methodCall = remoteDataSource.fetchNews())
                .thenReturn(NetworkResult.getNewsNetworkResultSuccess())
            newsRepository.observeNewsResponse().collect()
            verify(
                mock = localDataSource,
                mode = times(1)
            ).updateNewsLocal(anyList())
        }
    }

    @After
    fun after() {
        Mockito.clearAllCaches()
    }
}