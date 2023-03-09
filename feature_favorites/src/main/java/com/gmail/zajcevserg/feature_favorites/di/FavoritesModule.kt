package com.gmail.zajcevserg.feature_favorites.di

import com.gmail.zajcevserg.feature_favorites.impl.FavoritesImpl
import com.gmail.zajcevserg.feature_favorites_api.FavoritesApi
import dagger.Binds
import dagger.Module

@Module
interface FavoritesModule {

    @Binds
    fun bindFavorites(
        impl: FavoritesImpl
    ): FavoritesApi
}