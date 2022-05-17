package com.example.driverex.data.network

import com.example.driverex.utils.SharedPrefUtils
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    private val token = SharedPrefUtils.getSharedPrefAccessToken()

    override fun intercept(chain: Interceptor.Chain): Response {


    val request = chain.request()

        val shouldAddAuthHeaders = request.headers["isAuthorizable"] == "true"

        val requestBuilder = request
            .newBuilder()
            .method(request.method, request.body)
            .removeHeader("isAuthorizable")

                if (shouldAddAuthHeaders) {
                    request.newBuilder().addHeader("Authorization",token)
                }

        return chain.proceed(requestBuilder.build())
    }
}