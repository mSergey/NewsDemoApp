package com.gmail.zajcevserg.core_api.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites_article_table",
    indices = [Index(value = ["title","url"], unique = true)])
data class FavoritesArticleEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "source_name")
    val sourceName: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url_to_image")
    val urlToImage: String,
    @ColumnInfo(name = "url")
    val url: String
)