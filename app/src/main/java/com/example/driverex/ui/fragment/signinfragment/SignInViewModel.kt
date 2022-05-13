package com.example.driverex.ui.fragment.signinfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.driverex.data.model.ErrorResponse
import com.example.driverex.data.model.LoginResponse
import com.example.driverex.data.network.EmployeeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException

class SignInViewModel : ViewModel() {

    private val repository = EmployeeRepository()

    val isLoading : MutableLiveData<Boolean> = repository.isLoading
    val errorResponse : MutableLiveData<String> = repository.errorResponse
    val loginMessage : MutableLiveData<String> = repository.loginMessage
    val proceed : MutableLiveData<Boolean> = repository.proceed

    var loginResponse : MutableLiveData<LoginResponse> = repository.loginResponse



    fun login(email : String, password: String) : MutableLiveData<LoginResponse> {

        viewModelScope.launch {
            try {
                loginResponse = repository.userLogin(email,password)
            }
            catch (t : Throwable) {

                when (t) {
                    is IOException -> errorResponse.postValue("Network Failure")
                }
            }
        }
        return loginResponse
    }
}