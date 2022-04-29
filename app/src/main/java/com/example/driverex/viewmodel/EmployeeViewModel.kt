package com.example.driverex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.driverex.model.data.LoginResponse
import com.example.driverex.model.network.EmployeeRepository
import com.example.driverex.model.network.RetrofitService


class EmployeeViewModel : ViewModel() {

   val repository = EmployeeRepository()

    fun login(email : String, password: String) {
        repository.userLogin(email,password)

    }


    fun employeeData(token : String) {
        repository.employeeData(token)
    }


}