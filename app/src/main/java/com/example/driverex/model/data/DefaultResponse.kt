package com.example.driverex.model.data

data class DefaultResponse<T>(
    val `data`: T,
    val message: String?
)

data class LoginResponse(
    val access_token: String,
    val token_type: String,
)

data class ErrorResponse
    (
    val message : String
    )