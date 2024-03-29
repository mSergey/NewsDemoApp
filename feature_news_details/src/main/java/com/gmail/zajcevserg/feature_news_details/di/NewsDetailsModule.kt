package com.gmail.zajcevserg.feature_news_details.di

import com.gmail.zajcevserg.feature_news_details.data.NewsDetailsRepository
import com.gmail.zajcevserg.feature_news_details.data.NewsDetailsRepositoryImpl
import com.gmail.zajcevserg.feature_news_details.impl.NewsDetailsImpl
import com.gmail.zajcevserg.feature_news_details.presentation.DetailsUiState
import com.gmail.zajcevserg.feature_news_details_api.NewsDetailsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow

@Module
interface NewsDetailsModule {

    companion object {
        @Provides
        fun provideUiStateFlow(): MutableStateFlow<DetailsUiState> {
            return MutableStateFlow(
                value = DetailsUiState.createEmptyState()
            )
        }
    }
    @Binds
    fun bindDetails(
        navImpl: NewsDetailsImpl
    ): NewsDetailsApi

    @Binds
    fun bindRepository(
        repoImpl: NewsDetailsRepositoryImpl
    ): NewsDetailsRepository
}