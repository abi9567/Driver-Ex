package com.example.driverex.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
)

data class ErrorResponse
    (
    @SerializedName("message")
    val errorMessage : String
    )
