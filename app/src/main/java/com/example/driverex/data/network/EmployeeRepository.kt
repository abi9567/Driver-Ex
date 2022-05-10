package com.example.driverex.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.driverex.data.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeRepository {

    val loginResponse = MutableLiveData<LoginResponse>()
    val errorResponse = MutableLiveData<ErrorResponse>()

    var employeeErrorResponse = EmployeeErrorResponse("")

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

        Log.d("Token",token)

        RetrofitService.retrofitService().employeeData(token = "Bearer $token").enqueue(object : Callback<DefaultResponse<EmployeeResponse>?> {


            override fun onResponse(
                call: Call<DefaultResponse<EmployeeResponse>?>,
                response: Response<DefaultResponse<EmployeeResponse>?>
            ) {
                if (response.isSuccessful) {
                    proceed.value = true
                    employeeDetails.value = response.body()?.data?.data
                }


                else {
                    proceed.value = false
                    Log.d("REPOSIT",response.message())
                    employeeErrorResponse = EmployeeErrorResponse(response.message())
                }

            }

            override fun onFailure(call: Call<DefaultResponse<EmployeeResponse>?>, t: Throwable) {
                proceed.value = false
                Log.d("REPOSIT",t.message.toString())
                employeeErrorResponse = EmployeeErrorResponse(t.message.toString())
            }
        })

        proceed.value = false
        return employeeDetails

    }
}