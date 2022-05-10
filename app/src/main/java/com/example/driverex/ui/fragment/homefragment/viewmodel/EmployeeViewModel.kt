package com.example.driverex.ui.fragment.homefragment.viewmodel

import androidx.lifecycle.*
import com.example.driverex.data.model.*
import com.example.driverex.data.network.EmployeeRepository


class EmployeeViewModel : ViewModel() {

    val repository = EmployeeRepository()

    var employeeErrorResponse = repository.employeeErrorResponse

    fun employeeData(token : String) : MutableLiveData<List<EmployeeData>> {
        return repository.employeeData(token)
    }



}