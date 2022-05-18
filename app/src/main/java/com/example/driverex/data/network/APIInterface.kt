package com.example.driverex.data.network


import com.example.driverex.data.model.*
import com.example.driverex.data.model.response.DefaultResponse
import com.example.driverex.data.model.response.LoginResponse
import com.example.driverex.data.model.response.PagingResponse
import retrofit2.Response
import retrofit2.http.*


interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email : String,
        @Field ("password") password : String
    ) : DefaultResponse<LoginResponse>


    @GET("employees")
    suspend fun getEmployees(
    ) : DefaultResponse<PagingResponse<Employee>>

}