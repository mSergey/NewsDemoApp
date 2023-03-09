package com.gmail.zajcevserg.core_impl.datasource.di

import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource
import com.gmail.zajcevserg.core_impl.BuildConfig
import com.gmail.zajcevserg.core_impl.datasource.remote.AuthorizationInterceptor
import com.gmail.zajcevserg.core_impl.datasource.remote.NewsApiService
import com.gmail.zajcevserg.core_impl.datasource.remote.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_NEWS_URL = "https://newsapi.org/v2/"
@Module
interface RemoteDataSourceModule {
    companion object {
        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_NEWS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        @Singleton
        @Provides
        fun providesOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(AuthorizationInterceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideLoginInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )
            }
        }

        @Singleton
        @Provides
        fun provideNewsService(retrofit: Retrofit): NewsApiService {
            return retrofit.create(NewsApiService::class.java)
        }
    }
    @Binds
    fun provideRemoteNewsDataSource(impl: NewsRemoteDataSourceImpl): NewsRemoteDataSource
}