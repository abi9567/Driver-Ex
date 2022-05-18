package com.example.driverex.data.model.response

import com.google.gson.annotations.SerializedName

data class PagingResponse<T>
    (
        @SerializedName("data")
        val data: List<T>
    )
