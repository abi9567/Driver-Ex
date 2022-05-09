package com.example.driverex.data.network


import com.example.driverex.data.model.DefaultResponse
import com.example.driverex.data.model.EmployeeResponse
import com.example.driverex.data.model.LoginResponse
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
        @Header("Accept") accept:String ="application/json",
        @Header("Authorization") token : String

    ) : Call<DefaultResponse<EmployeeResponse>>

}