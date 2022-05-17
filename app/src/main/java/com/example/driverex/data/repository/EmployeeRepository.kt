package com.example.driverex.data.repository

import com.example.driverex.data.model.EmployeeResponse
import com.example.driverex.data.network.ApiResponse
import com.example.driverex.data.network.RetrofitService
import com.example.driverex.enums.ApiStatus


class EmployeeRepository {

    private val apiInterface = RetrofitService.retrofitService()
    lateinit var employeeResponse : ApiResponse<EmployeeResponse?>


    suspend fun employeeData(token : String) : ApiResponse<EmployeeResponse?> {

        employeeResponse = ApiResponse.loading()

        try {
            val response = apiInterface.employeeData(token = "Bearer $token")

            if (response.isSuccessful) {
                employeeResponse = ApiResponse.success(response.body()?.defaultData!!)
            }

        else {
                employeeResponse =
                    ApiResponse.error(message = response.message(), data = null)

        }

    } catch (e : Exception) {
            employeeResponse=ApiResponse.error(data = null, message = e.message.toString())
    }

        return employeeResponse
    }

}
