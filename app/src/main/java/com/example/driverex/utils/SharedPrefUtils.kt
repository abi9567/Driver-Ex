package com.example.driverex.utils

import android.content.SharedPreferences
import com.example.driverex.MyApplication
import com.example.driverex.R
import com.example.driverex.constants.ACCESS_TOKEN
import com.example.driverex.constants.FAV_KEY
import com.example.driverex.constants.IN_OR_OUT
import com.example.driverex.extention.sharedPref


private lateinit var sharedPref : SharedPreferences
var accessToken : String = ""
var loginCheck : String = ""


object SharedPrefUtils {

    init {
        sharedPref = MyApplication.appContext.sharedPref()
    }

    fun getSharedPrefAccessToken() : String {
        accessToken = sharedPref.getString(ACCESS_TOKEN, null).toString()
        return accessToken
    }

    fun setSharedPrefToken(token: String) {
        sharedPref.edit()
            .putString(ACCESS_TOKEN, token)
            .apply()
    }

    fun setLogINOut(input: String) {

        loginCheck = input
        sharedPref.edit()
            .putString(IN_OR_OUT, loginCheck)
            .apply()
    }

    fun getSharedPrefLogin() : String {
        loginCheck = sharedPref.getString(IN_OR_OUT, "OUT")!!
        return loginCheck
    }


    fun getSharedPrefFavData() : String {
        return  sharedPref.getString(FAV_KEY, "NO")!!

    }

    fun favouriteSharedPref() {
        sharedPref.edit()
            .putString(FAV_KEY, "YES")
            .apply()
    }
}