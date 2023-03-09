package com.gmail.zajcevserg.core_api.datasource.remote.dto

import com.gmail.zajcevserg.core_api.datasource.remote.dto.success.NewsDto

sealed class NewsNetworkResult {
    data class Success(val data: NewsDto) : NewsNetworkResult()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorData: NewsErrorDto?
    ) : NewsNetworkResult()
}