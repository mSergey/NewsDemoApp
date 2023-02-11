package com.gmail.zajcevserg.core_impl.datasource.remote

import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource
import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkDto
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRemoteDataSource {
    override suspend fun fetchNews(): NewsNetworkDto {
        val response = newsService.getNews()
        return response.body()!!
    }
}