package com.example.driverex.utils

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.driverex.MyApplication
import com.example.driverex.R


private lateinit var sharedPref : SharedPreferences
var accessToken : String = ""
val loginCheck = MutableLiveData<String>()
val favourite = MutableLiveData<String>()

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

        loginCheck.value = input
        sharedPref.edit()
            .putString(MyApplication.appContext.getString(R.string.sharedPrefLogCheck), loginCheck.value)
            .apply()
    }

    fun getSharedPrefLogin() : MutableLiveData<String> {

        loginCheck.value = sharedPref.getString(MyApplication.appContext.getString(R.string.sharedPrefLogCheck), null)
        return loginCheck
    }


    fun getSharedPrefFavData() : MutableLiveData<String> {
        favourite.value = sharedPref.getString(MyApplication.appContext.getString(R.string.sharedPrefFavKey), null)
        return favourite
    }

    fun favouriteSharedPref() {
        favourite.value = "YES"
        sharedPref.edit()
            .putString(MyApplication.appContext.getString(R.string.sharedPrefFavKey), favourite.value)
            .apply()
    }
}