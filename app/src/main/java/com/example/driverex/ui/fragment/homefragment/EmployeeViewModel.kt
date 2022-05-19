package com.example.driverex.ui.fragment.homefragment

import androidx.lifecycle.*

import com.example.driverex.data.model.Employee
import com.example.driverex.data.model.response.ApiResponse
import com.example.driverex.data.repository.EmployeeRepository
import com.example.driverex.enums.ApiStatus
import kotlinx.coroutines.launch


class EmployeeViewModel : ViewModel() {


    private val _employees = MutableLiveData<ApiResponse<List<Employee>?>>()
    val employees: LiveData<ApiResponse<List<Employee>?>> = _employees

    private val repository = EmployeeRepository()

    init {
        getEmployeeList()
    }

    private fun getEmployeeList() {
        viewModelScope.launch {
            _employees.postValue(ApiResponse(ApiStatus.LOADING, null, null))

            _employees.value = repository.employeeData()
        }
    }
}


