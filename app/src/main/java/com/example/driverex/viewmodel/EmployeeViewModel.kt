package com.example.driverex.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import androidx.navigation.navArgument
import com.example.driverex.MyApplication
import com.example.driverex.R
import com.example.driverex.model.data.EmployeeData
import com.example.driverex.model.data.LoginResponse
import com.example.driverex.model.network.EmployeeRepository
import com.example.driverex.model.network.RetrofitService
import com.example.driverex.utils.sharedPref
import java.util.logging.Handler


class EmployeeViewModel : ViewModel() {

    private val sharedPref : SharedPreferences
    val favourite = MutableLiveData<String>()
    val repository = EmployeeRepository()
    val loginCheck = MutableLiveData<String>()
    var accessToken : String = ""

    init {
        val context = MyApplication.appContext
        sharedPref = context.sharedPref()
    }


    fun getSharedPrefAccessToken () {
        accessToken = sharedPref.getString("ACCESS_TOKEN",null).toString()
    }

    fun setSharedPrefToken(token : String) {
        val context = MyApplication.appContext

            sharedPref.edit()
            .putString(context.getString(R.string.sharedPrefAccessToken),token)
            .apply()
    }


    fun setLogINOut(input : String) {
        val context = MyApplication.appContext
        loginCheck.value = input
        sharedPref.edit()
            .putString(context.getString(R.string.sharedPrefLogCheck),loginCheck.value)
            .apply()
    }

    fun getSharedPrefLogin() {
        val context = MyApplication.appContext
        loginCheck.value = sharedPref.getString(context.getString(R.string.sharedPrefLogCheck),null)
    }


    fun getSharedPrefFavData() {
        val context = MyApplication.appContext
        favourite.value = sharedPref.getString(context.getString(R.string.sharedPrefFavKey),null)
    }

    fun favouriteSharedPref() {
        val context = MyApplication.appContext
            favourite.value = "YES"
            sharedPref.edit()
                .putString(context.getString(R.string.sharedPrefFavKey),favourite.value)
                .apply()
    }

    fun login(email : String, password: String) : MutableLiveData<LoginResponse> {
        return repository.userLogin(email,password)
    }

    fun employeeData(token : String) : MutableLiveData<List<EmployeeData>> {
        return repository.employeeData(token)
    }
}