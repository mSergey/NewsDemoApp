package com.gmail.zajcevserg.features_shared.di

import com.gmail.zajcevserg.features_shared.data.NewsRepository
import com.gmail.zajcevserg.features_shared.data.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureNewsListModule {
    @Binds
    fun bindRepository(
        repoImpl: NewsRepositoryImpl
    ): NewsRepository
}