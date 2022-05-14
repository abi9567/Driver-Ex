package com.example.driverex.data.network

import com.example.driverex.data.repository.EmployeeRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor (private val authorizationRepository: EmployeeRepository) : Interceptor {

//    override fun intercept(chain: Interceptor.Chain): Response {
////        val request = chain.request().signedRequest()
//        return chain.proceed(request)
//    }
//
//}

//private fun Request.signedRequest(): Request {
//    val accessToken = EmployeeRepository().
//    return newBuilder()
//        .header("Authorization", "Bearer ${accessToken.rawToken}")
//        .build()
//}