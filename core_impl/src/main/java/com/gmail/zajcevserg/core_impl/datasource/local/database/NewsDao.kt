package com.gmail.zajcevserg.core_impl.datasource.local.database

import androidx.room.Dao
import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity

@Dao
interface NewsDao {
    suspend fun getNews(): List<NewsEntity>
}