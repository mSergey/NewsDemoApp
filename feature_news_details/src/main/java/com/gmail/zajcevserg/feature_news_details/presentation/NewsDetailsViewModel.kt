package com.gmail.zajcevserg.feature_news_details.presentation


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gmail.zajcevserg.feature_news_details.data.NewsDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailsViewModel(
    private val detailsRepository: NewsDetailsRepository,
    private val uiState: MutableStateFlow<DetailsUiState>
) : ViewModel() {

    fun observeUiState(): StateFlow<DetailsUiState> {
        return uiState
    }

    fun sendAction(action: DetailsAction) {
        when (action) {
            is DetailsAction.ActionGetDetails -> {
                viewModelScope.launch {
                    val details = detailsRepository.fetchNewsDetails(action.id)
                    val newState = uiState.value.copy(
                        detailsUiModel = details,
                        isInitial = false,
                        isInFavorites = false
                    )
                    uiState.emit(newState)
                    detailsRepository.isArticleInFavorites(details).onEach {
                        uiState.emit(
                            uiState.value.copy(
                                detailsUiModel = details,
                                isInitial = false,
                                isInFavorites = it
                            )
                        )
                    }.collect()
                }
            }
            is DetailsAction.ActionFavoritesButtonPressed -> {
                viewModelScope.launch {
                    if (uiState.value.isInFavorites) {
                        detailsRepository.removeFavorites(uiState.value.detailsUiModel.id)
                    } else {
                        detailsRepository.saveToFavorites(
                            articleUiModel = uiState.value.detailsUiModel
                        )
                    }
                }
            }
        }
    }
}

class NewsDetailsViewModelFactory @Inject constructor(
    private val repository: NewsDetailsRepository,
    private val uiState: MutableStateFlow<DetailsUiState>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(repository, uiState) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}