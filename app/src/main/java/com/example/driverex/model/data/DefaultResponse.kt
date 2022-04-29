package com.example.driverex.model.data

data class DefaultResponse<T>(
    val `data`: T,
    val message: String?
)