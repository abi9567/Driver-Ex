package com.example.driverex.data.network

import com.example.driverex.BuildConfig
import com.example.driverex.utils.SharedPrefUtils
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object RetrofitService {

    fun retrofitService() : APIInterface {

        val logging = HttpLoggingInterceptor()

//      Play Store
        if (BuildConfig.DEBUG) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit =  Retrofit.Builder()
            .baseUrl("http://training.pixbit.in/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            return retrofit.create(APIInterface::class.java)
    }
}