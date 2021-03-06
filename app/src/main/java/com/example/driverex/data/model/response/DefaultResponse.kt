package com.example.driverex.data.model.response

import com.google.gson.annotations.SerializedName

data class DefaultResponse<T>
    (
    @SerializedName("data")
    val data: T?,

    @SerializedName("message")
    val message: String?
    )
