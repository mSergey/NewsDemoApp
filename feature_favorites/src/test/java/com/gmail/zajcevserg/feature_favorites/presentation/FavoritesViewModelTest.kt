package com.gmail.zajcevserg.feature_favorites.presentation

import app.cash.turbine.test
import com.gmail.zajcevserg.feature_favorites.data.FavoritesRepository
import com.gmail.zajcevserg.feature_favorites.testutils.TestUtils
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
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest {

    private val repository: FavoritesRepository = mock()
    private val uiState: MutableStateFlow<FavoritesUiState> = MutableStateFlow(
        value = FavoritesUiState.createEmptyState()
    )
    private lateinit var viewModel: FavoritesViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun before() {
        viewModel = initViewModelInstance()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `should emit initial state when subscribed before send action get`() {

        runBlocking {
            viewModel.observeUiState().test {
                assertEquals(awaitItem(), FavoritesUiState.createEmptyState())
            }
        }
    }

    @Test
    fun `should emit data when action get`() {
        runBlocking {
            whenever(methodCall = repository.getFavoritesArticles())
                .thenReturn(TestUtils.getFavoritesArticleUiModelsFlow())
            viewModel.sendAction(action = FavoritesAction.ActionGet)
            viewModel.observeUiState().test {
                val expected = TestUtils.getUiStateWithData()
                val actual = awaitItem()
                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `should call repository get articles function when action get`() {
        runBlocking {
            viewModel.sendAction(action = FavoritesAction.ActionGet)
            verify(
                mock = repository,
                mode = times(1)
            ).getFavoritesArticles()
        }
    }

    @Test
    fun `should call repository delete function with specify arg when action delete`() {
        runBlocking {
            val idArg = 0L
            viewModel.sendAction(action = FavoritesAction.ActionDelete(id = idArg))
            verify(
                mock = repository,
                mode = times(1)
            ).deleteArticleById(id = idArg)
        }
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        Mockito.clearAllCaches()
        uiState.value = FavoritesUiState.createEmptyState()
    }

    private fun initViewModelInstance(): FavoritesViewModel =
        FavoritesViewModel(
            repository = repository,
            uiState = uiState
        )
}