package com.example.driverex.data.model

data class DefaultResponse<T>(
    val `data`: T?,
    val message: String?
)
