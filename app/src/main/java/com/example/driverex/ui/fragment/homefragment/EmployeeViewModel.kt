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

    private val repository = EmployeeRepository()

    private val _employees = MutableLiveData<ApiResponse<EmployeeResponse?>>()
    val employees: LiveData<ApiResponse<EmployeeResponse?>> = _employees




    fun getEmployeeList() {
        val token = SharedPrefUtils.getSharedPrefAccessToken()
        viewModelScope.launch {
            try {
                val employeeResponse = repository.employeeData(token)
                if (employeeResponse.isSuccessful) {
                    _employees.value = ApiResponse(ApiStatus.SUCCESS,data = employeeResponse.body()?.defaultData, message = null)
                }

                else {
                    ApiResponse(ApiStatus.ERROR,message = employeeResponse.message(), data = null)
                }

            } catch (e : Exception) {
                ApiResponse.error(data = null, message = e.message.toString())
            }
        }

    }



//    fun employeeData(token : String) = liveData(Dispatchers.IO) {
//        emit(ApiResponse.loading())
//
//            try {
//                val employeeResponse = repository.employeeData(token)
//
//                if (employeeResponse.isSuccessful) {
//                    emit(ApiResponse.success(employeeResponse))
//                }
//                else {
//                    emit(ApiResponse.error(data = null, message = employeeResponse.message()))
//                }
//
//            }
//            catch (e : Exception) {
//                    emit(ApiResponse.error(data = null, message = e.message.toString() ))
//            }
//        }
//


}