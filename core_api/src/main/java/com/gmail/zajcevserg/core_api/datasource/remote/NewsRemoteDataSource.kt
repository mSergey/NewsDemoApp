package com.gmail.zajcevserg.core_api.datasource.remote

import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkDto

interface NewsRemoteDataSource {
    suspend fun fetchNews(): NewsNetworkDto
}