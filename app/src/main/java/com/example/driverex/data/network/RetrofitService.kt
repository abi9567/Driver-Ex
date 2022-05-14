package com.example.driverex.data.network

import android.media.session.MediaSession
import com.example.driverex.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object RetrofitService {

    fun retrofitService() : APIInterface {

        val logging = HttpLoggingInterceptor()

        val token : String

//        val headerInterceptor =  object : Interceptor {
//            override fun intercept(chain: Interceptor.Chain): Response {
//                var request: Request = chain.request()
//                request = request.newBuilder()
//                    .addHeader("Authorization","Bearer $token")
//                    .build()
//                return chain.proceed(request)
//            }
//
//        }



//      Play Store
        if (BuildConfig.DEBUG) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
//            .addInterceptor(headerInterceptor)
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