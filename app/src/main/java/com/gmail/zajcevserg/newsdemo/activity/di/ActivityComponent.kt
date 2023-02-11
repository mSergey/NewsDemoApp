package com.gmail.zajcevserg.newsdemo.activity.di


import android.content.Context
import com.gmail.zajcevserg.core_api.providers.AppContextProvider
import com.gmail.zajcevserg.core_api.providers.ProvidersFacade
import com.gmail.zajcevserg.features_shared.di.FeatureNewsListModule
import com.gmail.zajcevserg.newsdemo.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(
    dependencies = [ProvidersFacade::class],
    modules = [FeatureNewsListModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}

