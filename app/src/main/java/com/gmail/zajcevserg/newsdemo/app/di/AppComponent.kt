package com.gmail.zajcevserg.newsdemo.app.di


import android.content.Context
import com.gmail.zajcevserg.core_api.providers.AppContextProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent : AppContextProvider {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}

