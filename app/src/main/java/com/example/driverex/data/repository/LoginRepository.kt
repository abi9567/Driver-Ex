package com.example.driverex.data.repository

import com.example.driverex.data.network.RetrofitService

class LoginRepository {

    private val response = RetrofitService.retrofitService()

    suspend fun userLogin(userName: String, password: String) = response.userLogin(userName, password)
}