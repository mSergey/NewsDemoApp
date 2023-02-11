package com.gmail.zajcevserg.core_api.datasource.local

interface NewsLocalDataSource {
    suspend fun getNews(): List<NewsEntity>
}