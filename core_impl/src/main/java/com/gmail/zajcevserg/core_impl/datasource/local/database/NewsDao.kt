package com.gmail.zajcevserg.core_impl.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NewsDao {
    @Query("SELECT * FROM news_cache_table")
    abstract fun getNews(): Flow<List<NewsEntity>>

    @Transaction
    open suspend fun updateNewsCache(newsEntities: List<NewsEntity>) {
        deleteAll()
        newsEntities.forEach {
            insertNews(it)
        }
    }

    @Insert
    abstract suspend fun insertNews(newsEntity: NewsEntity)

    @Query("DELETE FROM news_cache_table")
    abstract suspend fun deleteAll()

    @Query("SELECT * FROM news_cache_table WHERE id = :id LIMIT 1")
    abstract fun getNewsById(id: Long): NewsEntity
}