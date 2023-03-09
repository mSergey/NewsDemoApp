package com.gmail.zajcevserg.core_impl.datasource.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

object AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val request = originalRequest.newBuilder()
            .header("Authorization", "7fa5fc07bf62441e94165374234ab6e4")
            .build()
        return chain.proceed(request)
    }
}