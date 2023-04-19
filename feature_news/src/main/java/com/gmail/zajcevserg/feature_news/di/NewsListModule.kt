package com.gmail.zajcevserg.feature_news.di

import com.gmail.zajcevserg.feature_news.data.NewsRepository
import com.gmail.zajcevserg.feature_news.data.NewsRepositoryImpl
import com.gmail.zajcevserg.feature_news.impl.NewsImpl
import com.gmail.zajcevserg.feature_news.presentation.NewsListUiState
import com.gmail.zajcevserg.feature_news_api.NewsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.MutableStateFlow

@Module
interface NewsListModule {

    companion object {
        @Provides
        fun provideUiStateFlow(): MutableStateFlow<NewsListUiState> {
            return MutableStateFlow(
                value = NewsListUiState.createEmptyState()
            )
        }
    }

    @Binds
    fun bindDetails(
        navImpl: NewsImpl
    ): NewsApi

    @Binds
    fun bindRepository(
        repoImpl: NewsRepositoryImpl
    ): NewsRepository

}