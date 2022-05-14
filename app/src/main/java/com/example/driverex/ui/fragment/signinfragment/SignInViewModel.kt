package com.example.driverex.ui.fragment.signinfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.driverex.enums.ApiResponse
import com.example.driverex.data.network.EmployeeRepository
import kotlinx.coroutines.Dispatchers

class SignInViewModel : ViewModel() {

    private val repository = EmployeeRepository()

    fun login(email : String, password: String) = liveData(Dispatchers.IO) {

            emit(ApiResponse.loading())
            try {
                val loginResponse = repository.userLogin(email,password)
                if (loginResponse.isSuccessful)
                    emit(ApiResponse.success(loginResponse))

                else {
                    emit(ApiResponse.error(data = null,loginResponse.message().toString()))
                }
            }
            catch (e : Exception) {
                emit(ApiResponse.error(data = null, message = e.message.toString()))
            }
        }

    }
