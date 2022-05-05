package com.example.driverex.utils

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.driverex.MyApplication
import com.example.driverex.R


private lateinit var sharedPref : SharedPreferences
var accessToken : String = ""
var loginCheck : String = ""


object SharedPrefUtils {

    init {
        sharedPref = MyApplication.appContext.sharedPref()
    }

    fun getSharedPrefAccessToken() : String {
        accessToken = sharedPref.getString("ACCESS_TOKEN", null).toString()
        return accessToken
    }

    fun setSharedPrefToken(token: String) {
        sharedPref.edit()
            .putString(MyApplication.appContext.getString(R.string.sharedPrefAccessToken), token)
            .apply()
    }

    fun setLogINOut(input: String) {

        loginCheck = input
        sharedPref.edit()
            .putString(MyApplication.appContext.getString(R.string.sharedPrefLogCheck), loginCheck)
            .apply()
    }

    fun getSharedPrefLogin() : String {
        loginCheck = sharedPref.getString(MyApplication.appContext.getString(R.string.sharedPrefLogCheck), "OUT")!!
        return loginCheck
    }


    fun getSharedPrefFavData() : String {
        return  sharedPref.getString(MyApplication.appContext.getString(R.string.sharedPrefFavKey), "NO")!!

    }

    fun favouriteSharedPref() {
        sharedPref.edit()
            .putString(MyApplication.appContext.getString(R.string.sharedPrefFavKey), "YES")
            .apply()
    }
}