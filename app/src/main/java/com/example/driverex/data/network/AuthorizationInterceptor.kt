package com.example.driverex.data.network

import com.example.driverex.utils.SharedPrefUtils
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    private val token = SharedPrefUtils.getSharedPrefAccessToken()

    override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
        request.newBuilder()
               .addHeader("Authorization", token)
               .build()
        return chain.proceed(request)
    }
}