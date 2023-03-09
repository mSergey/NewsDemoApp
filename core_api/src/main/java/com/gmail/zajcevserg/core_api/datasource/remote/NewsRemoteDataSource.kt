package com.gmail.zajcevserg.core_api.datasource.remote

import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkResult

interface NewsRemoteDataSource {
    suspend fun fetchNews(): NewsNetworkResult
}