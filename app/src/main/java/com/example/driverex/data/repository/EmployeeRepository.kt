package com.example.driverex.data.repository

import com.example.driverex.data.network.RetrofitService

class EmployeeRepository {

    private val response = RetrofitService.retrofitService()

    suspend fun employeeData(token : String) = response.employeeData(token = "Bearer $token")

}
