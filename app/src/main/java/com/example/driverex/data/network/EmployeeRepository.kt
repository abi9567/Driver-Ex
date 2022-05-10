package com.example.driverex.data.network

import android.accounts.NetworkErrorException
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
import java.lang.Exception


class EmployeeRepository {
    private val loginResponse = MutableLiveData<LoginResponse>()
    val errorResponse = MutableLiveData<ErrorResponse>()
    var employeeErrorResponse = EmployeeErrorResponse("")
    val loginMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)
    val proceed = MutableLiveData<Boolean>(false)
    private val employeeDetails = MutableLiveData<List<EmployeeData>>()

    private val response = RetrofitService.retrofitService()

    fun userLogin(userName: String, password: String): MutableLiveData<LoginResponse> {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val userLogin = response.userLogin(userName, password)

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
                Log.d("Exception", userLogin.message())
            }

            } catch (e: NetworkErrorException) {
                errorResponse.value = ErrorResponse(e.message.toString())
                Log.d("Exception", e.message.toString())
            }
        }
        return loginResponse
    }

    fun employeeData(token: String): MutableLiveData<List<EmployeeData>> {

        CoroutineScope(Dispatchers.Main).launch {

            try {
                val employeeData = response.employeeData(token = "Bearer $token")
                if (employeeData.isSuccessful) {
                    proceed.value = true
                    employeeDetails.value = employeeData.body()?.defaultData?.employeeData
                } else {
                    proceed.value = false
                    Log.d("REPOSIT", employeeData.code().toString())
                    employeeErrorResponse = EmployeeErrorResponse(employeeData.message())
                }
            }
            catch (e:Exception) {
                employeeErrorResponse = EmployeeErrorResponse(e.message.toString())
            }
        }
        return employeeDetails
    }
}
