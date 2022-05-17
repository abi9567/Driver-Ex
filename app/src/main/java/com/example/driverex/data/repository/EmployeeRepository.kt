package com.example.driverex.data.repository

import com.example.driverex.data.model.EmployeeResponse
import com.example.driverex.data.network.ApiResponse
import com.example.driverex.data.network.RetrofitService
import com.example.driverex.enums.ApiStatus


class EmployeeRepository {

    private val apiInterface = RetrofitService.retrofitService()
    lateinit var employeeResponse : ApiResponse<EmployeeResponse>


    suspend fun employeeData(token : String) : ApiResponse<EmployeeResponse> {

        try {
            val response = apiInterface.employeeData(token = "Bearer $token")

            if (response.isSuccessful) {
                employeeResponse = ApiResponse(ApiStatus.SUCCESS,response.body()?.defaultData!!,null)
            }

        else {
                ApiResponse(ApiStatus.ERROR,message = response.message(), data = null)

        }

    } catch (e : Exception) {
        ApiResponse.error(data = null, message = e.message.toString())
    }

        return employeeResponse
    }

}
