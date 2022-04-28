package com.example.driverex.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    fun retrofitService() : APIInterface {

        val retrofit =  Retrofit.Builder()
            .baseUrl("http://training.pixbit.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            return retrofit.create(APIInterface::class.java)
    }

}