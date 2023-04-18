package com.gmail.zajcevserg.core_api.datasource.local

import kotlinx.coroutines.flow.Flow
import java.nio.file.LinkOption

interface NewsLocalDataSource {
    suspend fun getNews(): Flow<List<NewsEntity>>
    suspend fun getFavoritesArticles(): Flow<List<FavoritesArticleEntity>>
    suspend fun deleteFavoritesArticleById(id: Long)
    suspend fun addArticleToFavorites(entity: FavoritesArticleEntity)
    suspend fun getNewsDetailsById(id: Long): NewsEntity
    suspend fun updateNewsLocal(newsEntities: List<NewsEntity>)
}