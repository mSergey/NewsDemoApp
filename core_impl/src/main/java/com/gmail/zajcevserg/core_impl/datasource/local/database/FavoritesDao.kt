package com.gmail.zajcevserg.core_impl.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.zajcevserg.core_api.datasource.local.FavoritesArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoritesDao {
    @Query("SELECT * FROM favorites_article_table")
    abstract fun getFavorites(): Flow<List<FavoritesArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addArticleToFavorites(entity: FavoritesArticleEntity)

    @Query("DELETE FROM favorites_article_table WHERE id = :id")
    abstract suspend fun deleteFavoritesArticleById(id: Long)
}