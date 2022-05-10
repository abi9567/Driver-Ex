package com.example.driverex.data.model

import com.google.gson.annotations.SerializedName

data class DefaultResponse<T>(
    @SerializedName("data")
    val defaultData: T?,

    @SerializedName("message")
    val message: String?
)
