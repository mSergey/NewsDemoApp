package com.gmail.zajcevserg.core_impl.datasource.local

import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import com.gmail.zajcevserg.core_api.datasource.local.NewsLocalDataSource
import com.gmail.zajcevserg.core_impl.datasource.local.database.NewsDao
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val dao: NewsDao
) : NewsLocalDataSource {

    override suspend fun getNews(): List<NewsEntity> {
        return dao.getNews()
    }

}