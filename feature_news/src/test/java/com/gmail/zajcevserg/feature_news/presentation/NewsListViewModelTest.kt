package com.gmail.zajcevserg.feature_news.presentation

import app.cash.turbine.test
import com.gmail.zajcevserg.feature_news.data.NewsRepository
import com.gmail.zajcevserg.feature_news.testutils.UiResponse
import com.gmail.zajcevserg.feature_news.testutils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest {

    private val repository: NewsRepository = mock()
    private val uiState: MutableStateFlow<NewsListUiState> = MutableStateFlow(
        value = NewsListUiState.createEmptyState()
    )
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: NewsListViewModel

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `should emit success data with when action refresh`() {
        whenever(methodCall = repository.observeNewsResponse())
            .thenReturn(UiResponse.getNewsUiResponseNewsDataFlow())
        viewModel = initViewModelInstance()
        viewModel.sendAction(action = NewsListAction.ActionRefresh)
        runBlocking {
            viewModel.observeUiState().test {
                val expected = UiState.getUiStateWithNewsData()
                val actual = awaitItem()
                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should emit data with server error when action refresh`() {
        whenever(methodCall = repository.observeNewsResponse())
            .thenReturn(UiResponse.getNewsUiResponseNetworkErrorFlow())
        viewModel = initViewModelInstance()
        viewModel.sendAction(action = NewsListAction.ActionRefresh)
        runBlocking {
            viewModel.observeUiState().test {
                val expected = UiState.getUiStateWithNetworkError()
                val actual = awaitItem()
                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should emit data with network error when action refreshe`() {
        whenever(methodCall = repository.observeNewsResponse())
            .thenReturn(UiResponse.getNewsUiResponseServerErrorFlow())
        viewModel = initViewModelInstance()
        viewModel.sendAction(action = NewsListAction.ActionRefresh)
        runBlocking {
            viewModel.observeUiState().test {
                val expected = UiState.getUiStateWithServerError()
                val actual = awaitItem()
                assertEquals(expected, actual)
            }
        }
    }
    @After
    fun after() {
        Dispatchers.resetMain()
        uiState.value = NewsListUiState.createEmptyState()
        Mockito.clearAllCaches()
    }

    private fun initViewModelInstance(): NewsListViewModel =
        NewsListViewModel(
            repository = repository,
            uiState = uiState
        )

}