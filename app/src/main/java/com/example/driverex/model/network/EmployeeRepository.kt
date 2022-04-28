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

    val loginResponse = MutableLiveData<DefaultResponse<LoginResponse>?>()
    val errorResponse = MutableLiveData<DefaultResponse<ErrorResponse>>()

    fun userLogin(userName : String, password : String) : MutableLiveData<DefaultResponse<LoginResponse>?> {

        RetrofitService.retrofitService().userLogin(userName,password)
            .enqueue(object : Callback<DefaultResponse<LoginResponse>?> {
                override fun onResponse(
                    call: Call<DefaultResponse<LoginResponse>?>,
                    response: Response<DefaultResponse<LoginResponse>?>
                ) {
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()
                    }

                    else {
                        loginResponse.value = null
                    }


                }

                override fun onFailure(call: Call<DefaultResponse<LoginResponse>?>, t: Throwable) {

                    Log.d("RETRO",t.message.toString())

                }
            })

        return loginResponse
    }

}