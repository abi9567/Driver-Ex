package com.example.driverex.ui.fragment.signinfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.driverex.data.network.ApiResponse
import com.example.driverex.data.repository.EmployeeRepository
import com.example.driverex.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

class SignInViewModel : ViewModel() {

    private val repository = LoginRepository()

    fun login(email : String, password: String) = liveData(Dispatchers.IO) {

            emit(ApiResponse.loading())


            try {
                val loginResponse = repository.userLogin(email,password)
                if (loginResponse.isSuccessful)
                    emit(ApiResponse.success(loginResponse))
                else {
                    Log.d("ErrorRaw",loginResponse.raw().toString())
                    Log.d("ErrorBody",loginResponse.errorBody().toString())
                    Log.d("Error",loginResponse.body()?.message.toString())
                    emit(ApiResponse.error(data = null,loginResponse.message().toString()))
                }
            }
            catch (e : Exception) {
                emit(ApiResponse.error(data = null, message = e.message.toString()))
            }
        }

    }
