package com.example.driverex.viewmodel

import androidx.lifecycle.*
import com.example.driverex.data.model.*
import com.example.driverex.data.network.EmployeeRepository

import com.example.driverex.utils.SharedPrefUtils


class EmployeeViewModel : ViewModel() {


    val repository = EmployeeRepository()
    val isLoading : MutableLiveData<Boolean> = repository.isLoading
    val errorResponse : MutableLiveData<ErrorResponse> = repository.errorResponse

    var employeeErrorResponse = repository.employeeErrorResponse

    val loginMessage : MutableLiveData<String> = repository.loginMessage
    val proceed : MutableLiveData<Boolean> = repository.proceed



    fun getSharedPrefAccessToken() : String {
        return SharedPrefUtils.getSharedPrefAccessToken()
    }

    fun getSharedPrefLogin() : String {
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

    fun getSharedPrefFavData() : String {
        return SharedPrefUtils.getSharedPrefFavData()
    }

    fun favouriteSharedPref() {
        SharedPrefUtils.favouriteSharedPref()
    }

}