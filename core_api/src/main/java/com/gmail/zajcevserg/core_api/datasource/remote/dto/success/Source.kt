package com.gmail.zajcevserg.core_api.datasource.remote.dto.success

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
)

