package com.gmail.zajcevserg.core_impl.datasource.remote

import com.gmail.zajcevserg.core_api.datasource.remote.NewsRemoteDataSource
import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsErrorDto
import com.gmail.zajcevserg.core_api.datasource.remote.dto.NewsNetworkResult
import com.gmail.zajcevserg.core_api.datasource.remote.dto.success.NewsDto
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {

    override suspend fun fetchNews(): NewsNetworkResult {
        return try {
            val result = newsApiService.fetchNews()
            if (result.isSuccessful && result.body() != null)
                handleSuccessResult(result)
             else handleServerErrorResult(result)
        } catch (e: Exception) {
            handleNetworkError()
        }
    }

    private fun handleSuccessResult(
        result: Response<NewsDto>
    ): NewsNetworkResult {
        return NewsNetworkResult.Success(
            data = result.body()!!
        )
    }

    private fun handleServerErrorResult(
        result: Response<NewsDto>
    ): NewsNetworkResult {
        val error = result.errorBody()?.string()
        val gson = Gson()
        val errorDto = gson.fromJson(error, NewsErrorDto::class.java)
        return NewsNetworkResult.Failure(
            errorData = errorDto,
            errorCode = result.code(),
            isNetworkError = false
        )
    }

    private fun handleNetworkError(): NewsNetworkResult {
        return NewsNetworkResult.Failure(
            errorData = null,
            errorCode = null,
            isNetworkError = true
        )
    }
}