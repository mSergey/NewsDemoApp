package com.gmail.zajcevserg.core_api.datasource.local

import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun getNews(): Flow<List<NewsEntity>>
    suspend fun getNewsDetailsById(id: Long): NewsEntity
    suspend fun updateNewsLocal(newsEntities: List<NewsEntity>)
}