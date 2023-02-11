package com.gmail.zajcevserg.core_impl.datasource.di

import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource
import com.gmail.zajcevserg.core_impl.BuildConfig
import com.gmail.zajcevserg.core_impl.datasource.remote.NewsRemoteDataSourceImpl
import com.gmail.zajcevserg.core_impl.datasource.remote.NewsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
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
        fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor {
                    val originalRequest: Request = it.request()
                    val request = originalRequest.newBuilder()
                        .header("Authorization", "7fa5fc07bf62441e94165374234ab6e4")
                        .build()
                    it.proceed(request)
                }.build()
        }
        @Singleton
        @Provides
        fun createLoginInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
            return interceptor
        }
        @Singleton
        @Provides
        fun provideNewsService(retrofit: Retrofit): NewsService {
            return retrofit.create(NewsService::class.java)
        }
    }
    @Binds
    fun provideRemoteNewsDataSource(impl: NewsRemoteDataSourceImpl): NewsRemoteDataSource
}