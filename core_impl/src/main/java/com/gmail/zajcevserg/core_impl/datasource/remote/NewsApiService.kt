package com.gmail.zajcevserg.core_impl.datasource.remote

import com.gmail.zajcevserg.core_api.datasource.remote.dto.success.NewsDto
import retrofit2.Response
import retrofit2.http.GET

interface NewsApiService {
    @GET("everything?q=android")
    suspend fun fetchNews(): Response<NewsDto>
}