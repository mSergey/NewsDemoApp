package com.gmail.zajcevserg.core_impl.datasource.di

import android.content.Context
import androidx.room.Room
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.core_impl.datasource.local.NewsLocalDataSourceImpl
import com.gmail.zajcevserg.core_impl.datasource.local.database.NewsDao
import com.gmail.zajcevserg.core_impl.datasource.local.database.NewsDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATABASE_NAME = "NewsDatabase"
@Module
interface LocalDataSourceModule {
    companion object {
        @Provides
        @Singleton
        fun provideDatabase(context: Context): NewsDatabase {
            return Room
                .databaseBuilder(context, NewsDatabase::class.java, DATABASE_NAME)
                .build()
        }

        @Provides
        fun provideNewsDao(database: NewsDatabase): NewsDao {
            return database.newsDao
        }

    }

    @Binds
    fun provideNewsLocalDataSource(impl: NewsLocalDataSourceImpl): NewsLocalDataSource
}