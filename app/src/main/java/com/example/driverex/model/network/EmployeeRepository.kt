package com.example.driverex.model.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driverex.model.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeRepository {

    val loginResponse = MutableLiveData<LoginResponse>()
    val errorResponse = MutableLiveData<ErrorResponse>()
    val loginMessage  = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>(false)
    val proceed = MutableLiveData<Boolean>(false)

    val employeeDetails = MutableLiveData<List<EmployeeData>>()

    fun userLogin(userName: String, password: String) : MutableLiveData<LoginResponse> {

        isLoading.value = true
        proceed.value = false

        RetrofitService.retrofitService().userLogin(userName, password)
            .enqueue(object : Callback<DefaultResponse<LoginResponse>?> {
                override fun onResponse(
                    call: Call<DefaultResponse<LoginResponse>?>,
                    response: Response<DefaultResponse<LoginResponse>?>
                ) {
                    Log.d("RETRO", response.message())
                    isLoading.value = false

//                    if (response.body()?.message.equals("User Logged in")) {

                    if (response.isSuccessful) {
                        proceed.value = true
                        loginMessage.value = response.body()?.message!!
                        loginResponse.value = response.body()?.data!!

                    }

                    if (!response.isSuccessful) {
                        proceed.value = false
                        //Un Authorised
                        Log.e("ResponseError", response.message())
                        errorResponse.value = ErrorResponse(response.message())
                    }
//
//                    else if (response.message().equals("Unauthorized")) {
//
//                    }
                }

                override fun onFailure(call: Call<DefaultResponse<LoginResponse>?>, t: Throwable) {
                    proceed.value = false
                    isLoading.value = false
                    errorResponse.value = ErrorResponse(t.message ?: "Something Went Wrong")
                }
            })

        proceed.value = false
        return loginResponse

    }

    fun employeeData(token: String) : MutableLiveData<List<EmployeeData>> {
        RetrofitService.retrofitService().employeeData("Bearer $token").enqueue(object : Callback<DefaultResponse<EmployeeResponse>?> {
            override fun onResponse(
                call: Call<DefaultResponse<EmployeeResponse>?>,
                response: Response<DefaultResponse<EmployeeResponse>?>
            ) {
                if (response.isSuccessful) {
                    employeeDetails.value = response.body()?.data?.data
                }
            }

            override fun onFailure(call: Call<DefaultResponse<EmployeeResponse>?>, t: Throwable) {
                Log.d("REPOSIT",t.message.toString())
                errorResponse.value = ErrorResponse(t.message.toString())
            }
        })

        return employeeDetails

    }
}