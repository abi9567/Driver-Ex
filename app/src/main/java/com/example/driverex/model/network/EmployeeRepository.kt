package com.example.driverex.model.network

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.driverex.model.data.DefaultResponse
import com.example.driverex.model.data.ErrorResponse
import com.example.driverex.model.data.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeRepository {

    val loginResponse = MutableLiveData<LoginResponse>()
    val errorResponse = MutableLiveData<ErrorResponse>()
    var isLoading = MutableLiveData<Boolean>(false)


    fun userLogin(userName : String, password : String) {

        isLoading.value = true

        RetrofitService.retrofitService().userLogin(userName,password)
            .enqueue(object : Callback<DefaultResponse<LoginResponse>?> {
                override fun onResponse(
                    call: Call<DefaultResponse<LoginResponse>?>,
                    response: Response<DefaultResponse<LoginResponse>?>
                ) {
                    Log.d("RETRO",response.raw().toString())
                    isLoading.value = false
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.data!!
                    }

                }

                override fun onFailure(call: Call<DefaultResponse<LoginResponse>?>, t: Throwable) {

                    isLoading.value = false
                    errorResponse.value = ErrorResponse(t.message?:"Something Went Wrong")

                }
            })


    }

}