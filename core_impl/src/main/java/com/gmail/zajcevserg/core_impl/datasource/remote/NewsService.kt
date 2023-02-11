package com.gmail.zajcevserg.core_impl.datasource.remote

import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkDto
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("everything?q=president")
    suspend fun getNews(): Response<NewsNetworkDto>
}

