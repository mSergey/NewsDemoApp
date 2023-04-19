package com.gmail.zajcevserg.feature_favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.zajcevserg.feature_favorites.data.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
class FavoritesViewModelFactory @Inject constructor(
    private val repository: FavoritesRepository,
    private val uiState: MutableStateFlow<FavoritesUiState>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(repository, uiState) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}