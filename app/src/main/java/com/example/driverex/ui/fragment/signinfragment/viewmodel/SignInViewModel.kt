package com.example.driverex.ui.fragment.signinfragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.driverex.data.model.ErrorResponse
import com.example.driverex.data.model.LoginResponse
import com.example.driverex.data.network.EmployeeRepository

class SignInViewModel : ViewModel() {

    private val repository = EmployeeRepository()

    val isLoading : MutableLiveData<Boolean> = repository.isLoading
    val errorResponse : MutableLiveData<ErrorResponse> = repository.errorResponse
    val loginMessage : MutableLiveData<String> = repository.loginMessage
    val proceed : MutableLiveData<Boolean> = repository.proceed

    fun login(email : String, password: String) : MutableLiveData<LoginResponse> {
        return repository.userLogin(email,password)
    }




}