package com.gmail.zajcevserg.newsdemo.activity.di


import com.gmail.zajcevserg.core_api.providers.ProvidersFacade
import com.gmail.zajcevserg.feature_else.di.ElseModule
import com.gmail.zajcevserg.feature_favorites.di.FavoritesModule
import com.gmail.zajcevserg.feature_news_details.di.NewsDetailsModule
import com.gmail.zajcevserg.feature_news.di.NewsListModule
import com.gmail.zajcevserg.newsdemo.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ProvidersFacade::class],
    modules = [
        NewsListModule::class,
        NewsDetailsModule::class,
        FavoritesModule::class,
        ElseModule::class
    ]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}

