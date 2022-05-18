package com.example.driverex.data.repository

import com.example.driverex.data.model.response.LoginResponse
import com.example.driverex.data.network.ApiResponse
import com.example.driverex.data.network.RetrofitService
import com.example.driverex.enums.ApiStatus


class LoginRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var loginResponse : ApiResponse<LoginResponse?>

    suspend fun userLogin(userName: String, password: String) : ApiResponse<LoginResponse?> {

        loginResponse = ApiResponse.loading()
        try {
            val response = apiInterface.userLogin(userName, password)
            loginResponse = ApiResponse.success(response.data)

        } catch (e : Exception) {
            loginResponse = ApiResponse.error(message = e.message.toString(), data = null)
        }
        return loginResponse
    }

}