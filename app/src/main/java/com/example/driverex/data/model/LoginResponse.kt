package com.example.driverex.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val access_token: String,
    @SerializedName("tokenType")
    val token_type: String,
)

data class ErrorResponse
    (
    @SerializedName("errorMessage")
    val message : String
    )
