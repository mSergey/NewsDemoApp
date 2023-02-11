package com.gmail.zajcevserg.core_impl.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.zajcevserg.core_api.datasource.local.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}