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
    val loginResponse = MutableLiveData<LoginResponse>()
    val errorResponse = MutableLiveData<String>()
    var employeeErrorResponse = MutableLiveData<EmployeeErrorResponse>()
    val loginMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)
    val proceed = MutableLiveData<Boolean>(false)
    val employeeDetails = MutableLiveData<List<EmployeeData>>()

    private val response = RetrofitService.retrofitService()

    suspend fun userLogin(userName: String, password: String): MutableLiveData<LoginResponse> {

            val userLogin = response.userLogin(userName, password)
            if (userLogin.isSuccessful)
            {
                loginMessage.postValue(userLogin.body()?.message!!)
                isLoading.postValue(true)
                proceed.postValue(true)
                loginResponse.postValue(userLogin.body()?.defaultData!!)
                Log.d("TOKEN", loginResponse.value?.accessToken.toString())
            } else
            {
                proceed.postValue(false)
                errorResponse.postValue(userLogin.message())
                Log.d("Exception", userLogin.message())
            }

        return loginResponse
    }

    suspend fun employeeData(token: String): MutableLiveData<List<EmployeeData>> {

                val employeeData = response.employeeData(token = "Bearer $token")
                if (employeeData.isSuccessful) {
                    proceed.postValue(true)
                    employeeDetails.postValue(employeeData.body()?.defaultData?.employeeData)
                } else {
                    proceed.postValue(false)
                    Log.d("REPOSIT", employeeData.code().toString())
                    employeeErrorResponse.postValue(EmployeeErrorResponse(employeeData.message()))
                }

        return employeeDetails
    }
}
