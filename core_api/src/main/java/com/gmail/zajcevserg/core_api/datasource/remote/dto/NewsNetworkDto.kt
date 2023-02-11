package com.gmail.zajcevserg.core_api.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsNetworkDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>
)

