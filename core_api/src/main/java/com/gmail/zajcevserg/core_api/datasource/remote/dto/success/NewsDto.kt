package com.gmail.zajcevserg.core_api.datasource.remote.dto.success

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>
)
