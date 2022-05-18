package com.example.driverex.ui.fragment.signinfragment

import androidx.lifecycle.*
import com.example.driverex.data.model.response.LoginResponse
import com.example.driverex.data.network.ApiResponse
import com.example.driverex.data.repository.LoginRepository
import com.example.driverex.enums.ApiStatus
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse?>>()
    val loginResponse : LiveData<ApiResponse<LoginResponse?>> = _loginResponse

    private val repository = LoginRepository()


    fun login(userName : String, password : String) {
        viewModelScope.launch {
            _loginResponse.value =  ApiResponse(ApiStatus.LOADING,null,null)
            _loginResponse.value = repository.userLogin(userName,password)
        }
    }







//    fun login(email : String, password: String) = liveData(Dispatchers.IO) {
//
//            emit(ApiResponse.loading())
//
//
//            try {
//                val loginResponse = repository.userLogin(email,password)
//                if (loginResponse.isSuccessful)
//                    emit(ApiResponse.success(loginResponse))
//                else {
//                    Log.d("ErrorRaw",loginResponse.raw().toString())
//                    Log.d("ErrorBody",loginResponse.errorBody().toString())
//                    Log.d("Error",loginResponse.body()?.message.toString())
//                    emit(ApiResponse.error(data = null,loginResponse.message().toString()))
//                }
//            }
//            catch (e : Exception) {
//                emit(ApiResponse.error(data = null, message = e.message.toString()))
//            }
//        }

    }
