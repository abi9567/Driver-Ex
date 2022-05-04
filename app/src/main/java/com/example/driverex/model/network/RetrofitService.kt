package com.example.driverex.model.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    fun retrofitService() : APIInterface {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit =  Retrofit.Builder()
            .baseUrl("http://training.pixbit.in/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            return retrofit.create(APIInterface::class.java)
    }
}