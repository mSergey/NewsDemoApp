package com.gmail.zajcevserg.core_api.providers

import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource


interface DataSourceProvider {
    fun provideNewsRemoteDataSource(): NewsRemoteDataSource
    fun provideNewsLocalDataSource(): NewsLocalDataSource
}
