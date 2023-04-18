package com.gmail.zajcevserg.core_impl.datasource.local

import com.gmail.zajcevserg.core_api.datasource.local.FavoritesArticleEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.core_impl.datasource.local.database.FavoritesDao
import com.gmail.zajcevserg.core_impl.datasource.local.database.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val favoritesDao: FavoritesDao,
) : NewsLocalDataSource {

    override suspend fun getNews(): Flow<List<NewsEntity>> {
        return newsDao.getNews()
    }

    override suspend fun getFavoritesArticles(): Flow<List<FavoritesArticleEntity>> {
        return favoritesDao.getFavorites()
    }

    override suspend fun deleteFavoritesArticleById(id: Long) {
        favoritesDao.deleteFavoritesArticleById(id)
    }

    override suspend fun addArticleToFavorites(entity: FavoritesArticleEntity) {
        favoritesDao.addArticleToFavorites(entity)
    }

    override suspend fun getNewsDetailsById(id: Long): NewsEntity {
        return newsDao.getNewsById(id)
    }

    override suspend fun updateNewsLocal(newsEntities: List<NewsEntity>) {
        newsDao.updateNewsCache(newsEntities)
    }

}