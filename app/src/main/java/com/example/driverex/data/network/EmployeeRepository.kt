package com.example.driverex.data.network

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.driverex.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeRepository {
    private val loginResponse = MutableLiveData<LoginResponse>()
    val errorResponse = MutableLiveData<ErrorResponse>()
    var employeeErrorResponse = EmployeeErrorResponse("")
    val loginMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)
    val proceed = MutableLiveData<Boolean>(false)

    private val response = RetrofitService.retrofitService()
    private val employeeDetails = MutableLiveData<List<EmployeeData>>()

    fun userLogin(userName: String, password: String): MutableLiveData<LoginResponse> {

        CoroutineScope(Dispatchers.Main).launch {
            val userLogin = response.userLogin(userName, password)
            proceed.value = true
            if (userLogin.isSuccessful)
            {
                loginMessage.value = userLogin.body()?.message!!
                isLoading.value = true
                proceed.value = true
                loginResponse.value = userLogin.body()?.defaultData!!
                Log.d("TOKEN", loginResponse.value?.accessToken.toString())
            } else
            {
                proceed.value = false
                errorResponse.value = ErrorResponse(userLogin.message())
            }
        }
        return loginResponse
    }

    fun employeeData(token: String): MutableLiveData<List<EmployeeData>> {

        CoroutineScope(Dispatchers.Main).launch {

            val employeeData = response.employeeData(token = "Bearer $token")

            if (employeeData.isSuccessful) {
                proceed.value = true
                employeeDetails.value = employeeData.body()?.defaultData?.employeeData
            } else {
                proceed.value = false
                Log.d("REPOSIT", employeeData.message())
                employeeErrorResponse = EmployeeErrorResponse(employeeData.message())
            }
        }

        return employeeDetails
    }
}
