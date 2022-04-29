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
    val isLoading = MutableLiveData<Boolean>(false)

    val employeeDetails = MutableLiveData<List<EmployeeData>>()


    fun userLogin(userName: String, password: String) {

        isLoading.value = true

        RetrofitService.retrofitService().userLogin(userName, password)
            .enqueue(object : Callback<DefaultResponse<LoginResponse>?> {
                override fun onResponse(
                    call: Call<DefaultResponse<LoginResponse>?>,
                    response: Response<DefaultResponse<LoginResponse>?>
                ) {
                    Log.d("RETRO", response.message())

                    isLoading.value = false
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.data!!
                    } else if (response.message().equals("Unauthorized")) {
                        Log.e("ResponseError", response.errorBody().toString())
                        errorResponse.value = ErrorResponse("Check Your Password")
                    }
                }

                override fun onFailure(call: Call<DefaultResponse<LoginResponse>?>, t: Throwable) {

                    isLoading.value = false
                    errorResponse.value = ErrorResponse(t.message ?: "Something Went Wrong")

                }
            })

    }

    fun employeeData(token: String) {
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




    }
}