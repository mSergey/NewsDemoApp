package com.gmail.zajcevserg.feature_news_details.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gmail.zajcevserg.feature_news_details.data.NewsDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailsViewModel(
    private val repository: NewsDetailsRepository,
    private val uiState: MutableStateFlow<NewsDetailsUiState>
) : ViewModel() {

    fun observeUiState(): StateFlow<NewsDetailsUiState> {
        return uiState
    }
    fun sendActionToGetNewState(id: Long) {
        viewModelScope.launch {
            val details = repository.fetchNewsDetails(id)
            val state: NewsDetailsUiState = NewsDetailsUiState(
                detailsUiModel = details,
                isInitial = false
            )
            uiState.emit(state)
        }
    }
}

class NewsDetailsViewModelFactory @Inject constructor(
    private val repository: NewsDetailsRepository,
    private val uiState: MutableStateFlow<NewsDetailsUiState>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(repository, uiState) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}