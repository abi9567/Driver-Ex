package com.example.driverex.data.network


import com.example.driverex.data.model.DefaultResponse
import com.example.driverex.data.model.EmployeeResponse
import com.example.driverex.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    fun userLogin
    (
        @Field("email") email : String,
        @Field ("password") password : String

    ) : Response<DefaultResponse<LoginResponse>>


    @GET("employees")
    fun employeeData
    (
        @Header("Accept") accept:String ="application/json",
        @Header("Authorization") token : String

    ) : Response<DefaultResponse<EmployeeResponse>>

}