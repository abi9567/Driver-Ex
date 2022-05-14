package com.example.driverex.ui.fragment.homefragment

import androidx.lifecycle.*
import com.example.driverex.data.network.ApiResponse
import com.example.driverex.data.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


class EmployeeViewModel : ViewModel() {

    private val repository = EmployeeRepository()

    fun employeeData(token : String) = liveData(Dispatchers.IO) {
        emit(ApiResponse.loading())

            try {
                val employeeResponse = repository.employeeData(token)

                if (employeeResponse.isSuccessful) {
                    emit(ApiResponse.success(employeeResponse))
                }
                else {
                    emit(ApiResponse.error(data = null, message = employeeResponse.message()))
                }

            }
            catch (e : Exception) {
                    emit(ApiResponse.error(data = null, message = e.message.toString() ))
            }
        }



}