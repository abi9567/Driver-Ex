package com.example.driverex.ui.fragment.homefragment

import androidx.lifecycle.*
import com.example.driverex.data.model.*
import com.example.driverex.data.network.EmployeeRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception


class EmployeeViewModel : ViewModel() {

    private val repository = EmployeeRepository()

    var employeeErrorResponse = repository.employeeErrorResponse
    var employeeData = repository.employeeDetails

    fun employeeData(token : String) : MutableLiveData<List<EmployeeData>> {

        viewModelScope.launch {

            try {
                employeeData = repository.employeeData(token)
            }
            catch (t : Throwable) {

                when (t)
                {
                    is IOException -> employeeErrorResponse.postValue(EmployeeErrorResponse("Network Error"))
                }

            }
        }
        return employeeData
    }

}