package com.gmail.zajcevserg.core_impl.datasource.local

import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.core_impl.datasource.local.database.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val dao: NewsDao
) : NewsLocalDataSource {

    override suspend fun getNews(): Flow<List<NewsEntity>> {
        return dao.getNews()
    }

    override suspend fun getNewsDetailsById(id: Long): NewsEntity {
        return dao.getNewsById(id)
    }

    override suspend fun updateNewsLocal(newsEntities: List<NewsEntity>) {
        dao.updateNewsCache(newsEntities)
    }

}