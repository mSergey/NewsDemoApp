package com.gmail.zajcevserg.core

import com.gmail.zajcevserg.core_api.providers.AppContextProvider
import com.gmail.zajcevserg.core_api.providers.DataSourceProvider
import com.gmail.zajcevserg.core_impl.datasource.di.DaggerDataSourceComponent

object CoreDataSourceComponentProvider {
    fun provideDataSourceComponent(
        appContextProvider: AppContextProvider
    ): DataSourceProvider {
        return DaggerDataSourceComponent.builder()
            .appContextProvider(appContextProvider)
            .build()
    }
}