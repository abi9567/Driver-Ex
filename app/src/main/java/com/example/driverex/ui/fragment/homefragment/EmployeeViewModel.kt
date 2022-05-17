package com.example.driverex.ui.fragment.homefragment

import androidx.lifecycle.*
import com.example.driverex.data.model.EmployeeData
import com.example.driverex.data.model.EmployeeResponse
import com.example.driverex.data.network.ApiResponse
import com.example.driverex.data.repository.EmployeeRepository
import com.example.driverex.enums.ApiStatus
import com.example.driverex.utils.SharedPrefUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class EmployeeViewModel : ViewModel() {



    private val _employees = MutableLiveData<ApiResponse<EmployeeResponse?>>()
    val employees: LiveData<ApiResponse<EmployeeResponse?>> = _employees

    private val repository = EmployeeRepository()


    fun getEmployeeList() {
        val token = SharedPrefUtils.getSharedPrefAccessToken()
        viewModelScope.launch {
        _employees.value = repository.employeeData(token)
            }
        }
    }


