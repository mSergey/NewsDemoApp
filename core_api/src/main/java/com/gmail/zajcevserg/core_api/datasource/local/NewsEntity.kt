package com.gmail.zajcevserg.core_api.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_cache_table")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "source_name")
    val sourceName: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "url_to_image")
    val urlToImage: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    @ColumnInfo(name = "content")
    val content: String
)