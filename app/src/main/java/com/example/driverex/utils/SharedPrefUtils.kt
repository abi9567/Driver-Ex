package com.example.driverex.utils

import android.content.SharedPreferences
import com.example.driverex.MyApplication
import com.example.driverex.constants.ACCESS_TOKEN
import com.example.driverex.constants.FAV_KEY
import com.example.driverex.constants.LOGIN_KEY
import com.example.driverex.extention.sharedPref


private lateinit var sharedPref : SharedPreferences


object SharedPrefUtils {

    init {
        sharedPref = MyApplication.appContext.sharedPref()
    }

    fun getSharedPrefAccessToken() : String? {
        return sharedPref.getString(ACCESS_TOKEN, null)
    }

    fun setSharedPrefToken(token: String) {
        sharedPref.edit()
            .putString(ACCESS_TOKEN, token)
            .apply()
    }

    fun setLogINOut(input: String) {
        sharedPref.edit()
            .putString(LOGIN_KEY, input)
            .apply()
    }

    fun getSharedPrefLogin() : String {
        return sharedPref.getString(LOGIN_KEY, "OUT")!!
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