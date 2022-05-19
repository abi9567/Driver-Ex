package com.example.driverex.data.repository

import com.example.driverex.data.model.Employee
import com.example.driverex.data.model.response.ApiResponse
import com.example.driverex.data.network.RetrofitService


class EmployeeRepository {

    private val apiInterface = RetrofitService.retrofitService()
    private lateinit var employee: ApiResponse<List<Employee>?>

    suspend fun employeeData(): ApiResponse<List<Employee>?> {
        employee = try {
            val response = apiInterface.getEmployees()
            ApiResponse.success(response.data?.data)
        } catch (e: Exception) {
            ApiResponse.error(data = null, message = e.message.toString())
        }

        return employee
    }

}
