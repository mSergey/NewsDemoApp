package com.gmail.zajcevserg.feature_news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gmail.zajcevserg.feature_news.data.NewsRepository
import com.gmail.zajcevserg.feature_news.domain.NewsUiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NewsListViewModel(
    repository: NewsRepository,
    private val uiState: MutableStateFlow<NewsListUiState>
) : ViewModel() {

    fun observeUiState(): StateFlow<NewsListUiState> {
        return uiState
    }

    init {
        repository.observeNewsResponse()
            .onEach { response ->
                when (response) {
                    is NewsUiResponse.NewsData -> {
                        val current = uiState.value
                        val new = current.copy(
                            articles = response.articles,
                            serverDoNotResponse = false,
                            noInternetConnection = false,
                            isInitial = false
                        )
                        uiState.emit(new)
                    }
                    is NewsUiResponse.NetworkError -> {
                        val currentState = uiState.value
                        val new = currentState.copy(
                            serverDoNotResponse = false,
                            noInternetConnection = true,
                            isInitial = false
                        )
                        uiState.emit(new)
                    }
                    is NewsUiResponse.ServerError -> {
                        val currentState = uiState.value
                        val new = currentState.copy(
                            serverDoNotResponse = true,
                            noInternetConnection = false,
                            isInitial = false
                        )
                        uiState.emit(new)
                    }
                }
            }
            .launchIn(viewModelScope)
    }



}

class NewsListViewModelFactory @Inject constructor(
    private val repository: NewsRepository,
    private val uiState: MutableStateFlow<NewsListUiState>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(repository, uiState) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}