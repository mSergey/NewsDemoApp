package com.gmail.zajcevserg.core_api.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsErrorDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String
)
