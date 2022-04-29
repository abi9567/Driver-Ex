package com.example.driverex.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.navigation.navArgument
import com.example.driverex.model.data.EmployeeData
import com.example.driverex.model.data.LoginResponse
import com.example.driverex.model.network.EmployeeRepository
import com.example.driverex.model.network.RetrofitService
import java.util.logging.Handler


class EmployeeViewModel : ViewModel() {

   val repository = EmployeeRepository()

    fun login(email : String, password: String) {
        repository.userLogin(email,password)

    }

    fun employeeData(token : String) {
        repository.employeeData(token)
    }


}