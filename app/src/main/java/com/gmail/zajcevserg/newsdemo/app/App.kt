package com.gmail.zajcevserg.newsdemo.app

import android.app.Application
import com.gmail.zajcevserg.core.CoreDataSourceComponentProvider
import com.gmail.zajcevserg.core_api.providers.AppWithFacade
import com.gmail.zajcevserg.core_api.providers.ProvidersFacade
import com.gmail.zajcevserg.newsdemo.app.di.DaggerAppComponent
import com.gmail.zajcevserg.newsdemo.app.di.DaggerFacadeComponent
import com.gmail.zajcevserg.newsdemo.app.di.FacadeComponent

class App : Application(), AppWithFacade {

    companion object {
        lateinit var facadeComponent: FacadeComponent
    }
    override fun onCreate() {
        super.onCreate()
        val appContextProvider = DaggerAppComponent.factory().create(this)
        val dataSourceProvider = CoreDataSourceComponentProvider.provideDataSourceComponent(appContextProvider)
        facadeComponent = DaggerFacadeComponent.builder()
            .appContextProvider(appContextProvider)
            .dataSourceProvider(dataSourceProvider)
            .build()
    }
    override fun getFacade(): ProvidersFacade {
        return facadeComponent
    }
}