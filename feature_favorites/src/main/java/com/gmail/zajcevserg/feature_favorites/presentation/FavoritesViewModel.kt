package com.gmail.zajcevserg.feature_favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.zajcevserg.feature_favorites.data.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository,
    private val uiState: MutableStateFlow<FavoritesUiState>
) : ViewModel() {

    fun observeUiState(): StateFlow<FavoritesUiState> {
        return uiState
    }

    fun sendAction(action: FavoritesAction) {
        when (action) {
            is FavoritesAction.ActionGet ->
                viewModelScope.launch {
                    repository.getFavoritesArticles()
                        .distinctUntilChanged()
                        .collect {
                            uiState.emit(
                                value = FavoritesUiState(
                                    favoritesArticles = it,
                                    isInitial = false
                                )
                            )
                        }
            }
            is FavoritesAction.ActionDelete ->
                viewModelScope.launch {
                    repository.deleteArticleById(action.id)
                }
        }
    }
}


