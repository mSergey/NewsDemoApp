package com.gmail.zajcevserg.core_impl.datasource.di

import com.gmail.zajcevserg.core_api.providers.AppContextProvider
import com.gmail.zajcevserg.core_api.providers.DataSourceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppContextProvider::class],
    modules = [RemoteDataSourceModule::class, LocalDataSourceModule::class]
)
interface DataSourceComponent : DataSourceProvider