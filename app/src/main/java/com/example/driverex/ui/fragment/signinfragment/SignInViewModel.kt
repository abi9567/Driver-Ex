package com.example.driverex.ui.fragment.signinfragment

import androidx.lifecycle.*
import com.example.driverex.data.model.response.LoginResponse
import com.example.driverex.data.model.response.ApiResponse
import com.example.driverex.data.repository.LoginRepository
import com.example.driverex.enums.ApiStatus
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse?>>()
    val loginResponse: LiveData<ApiResponse<LoginResponse?>> = _loginResponse

    private val repository = LoginRepository()


    fun login(userName: String, password: String) {
        viewModelScope.launch {
            _loginResponse.value = ApiResponse(ApiStatus.LOADING, null, null)
            _loginResponse.value = repository.userLogin(userName, password)
        }
    }
}
