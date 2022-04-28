package com.example.driverex.model.network


import com.example.driverex.model.data.DefaultResponse
import com.example.driverex.model.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    fun userLogin

    (
        @Field("email") email : String,
        @Field ("password") password : String

    ) : retrofit2.Call<DefaultResponse<LoginResponse>>




}