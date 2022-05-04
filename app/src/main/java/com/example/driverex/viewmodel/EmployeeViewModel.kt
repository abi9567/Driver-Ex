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
import com.example.driverex.model.data.DefaultResponse
import com.example.driverex.model.data.EmployeeData
import com.example.driverex.model.data.ErrorResponse
import com.example.driverex.model.data.LoginResponse
import com.example.driverex.model.network.EmployeeRepository
import com.example.driverex.model.network.RetrofitService
import com.example.driverex.utils.SharedPrefUtils
import com.example.driverex.utils.sharedPref
import java.util.logging.Handler


class EmployeeViewModel : ViewModel() {

//  private val sharedPref : SharedPreferences
    val favourite = MutableLiveData<String>()
    val repository = EmployeeRepository()
    val loginCheck = MutableLiveData<String>()
//  var accessToken : String = ""

    val isLoading : MutableLiveData<Boolean> = repository.isLoading
    val errorResponse : MutableLiveData<ErrorResponse> = repository.errorResponse
    val loginMessage : MutableLiveData<String> = repository.loginMessage
    val proceed : MutableLiveData<Boolean> = repository.proceed
    var loginResponse = MutableLiveData<LoginResponse>()


    fun getSharedPrefAccessToken() {
        SharedPrefUtils.getSharedPrefAccessToken()
    }

    fun getSharedPrefLogin() : MutableLiveData<String> {
        return SharedPrefUtils.getSharedPrefLogin()
    }

    fun setLogINOut(input : String) {
        SharedPrefUtils.setLogINOut(input)
    }

    fun setSharedPrefToken(token : String) {
        SharedPrefUtils.setSharedPrefToken(token)
    }

    fun login(email : String, password: String) : MutableLiveData<LoginResponse> {
        return repository.userLogin(email,password)
    }

    fun employeeData(token : String) : MutableLiveData<List<EmployeeData>> {
        return repository.employeeData(token)
    }

    fun getSharedPrefFavData() : MutableLiveData<String> {
        return SharedPrefUtils.getSharedPrefFavData()
    }

    fun favouriteSharedPref() {
        SharedPrefUtils.favouriteSharedPref()
    }

}