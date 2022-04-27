package com.example.driverex.network.response

data class LoginResponse(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val access_token: String,
        val token_type: String
    )
}