package com.example.driverex.model.network


import com.example.driverex.model.data.DefaultResponse
import com.example.driverex.model.data.EmployeeResponse
import com.example.driverex.model.data.LoginResponse
import retrofit2.Call
import retrofit2.http.*


interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    fun userLogin
    (
        @Field("email") email : String,
        @Field ("password") password : String

    ) : retrofit2.Call<DefaultResponse<LoginResponse>>


    @GET("employees")
    fun employeeData
    (
        @Header("Authorization") token : String
    ) : Call<DefaultResponse<EmployeeResponse>>

}