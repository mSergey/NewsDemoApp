package com.gmail.zajcevserg.newsdemo.app.di

import com.gmail.zajcevserg.core_api.providers.AppContextProvider
import com.gmail.zajcevserg.core_api.providers.DataSourceProvider
import com.gmail.zajcevserg.core_api.providers.ProvidersFacade
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        DataSourceProvider::class,
        AppContextProvider::class],
    modules = []
)
interface FacadeComponent : ProvidersFacade
