package com.gmail.zajcevserg.feature_favorites.di

import com.gmail.zajcevserg.feature_favorites.data.FavoritesRepositoryImpl
import com.gmail.zajcevserg.feature_favorites.data.FavoritesRepository
import com.gmail.zajcevserg.feature_favorites.impl.FavoritesImpl
import com.gmail.zajcevserg.feature_favorites.presentation.FavoritesUiState
import com.gmail.zajcevserg.feature_favorites_api.FavoritesApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow

@Module
interface FavoritesModule {

    companion object {
        @Provides
        fun provideUiStateFlow(): MutableStateFlow<FavoritesUiState> {
            return MutableStateFlow(
                value = FavoritesUiState.createEmptyState()
            )
        }
    }

    @Binds
    fun bindFavorites(
        impl: FavoritesImpl
    ): FavoritesApi

    @Binds
    fun bindRepository(
        repoImpl: FavoritesRepositoryImpl
    ): FavoritesRepository
}