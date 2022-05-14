package com.example.driverex.data.network

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.driverex.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class EmployeeRepository {

    private val response = RetrofitService.retrofitService()

    suspend fun userLogin(userName: String, password: String) = response.userLogin(userName, password)

    suspend fun employeeData(token : String) = response.employeeData(token = "Bearer $token")

}
