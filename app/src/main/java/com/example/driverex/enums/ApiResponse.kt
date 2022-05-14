package com.example.driverex.enums

data class ApiResponse<T>(val apiStatus : ApiStatus, val data :T, val message : String?) {

    companion object {
        fun <T> success(data: T): ApiResponse<T> =
            ApiResponse(apiStatus = ApiStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiResponse<T?> =
            ApiResponse(apiStatus = ApiStatus.ERROR, data = data, message = message)

        fun <T> loading(): ApiResponse<T?> =
            ApiResponse(apiStatus = ApiStatus.LOADING, data = null, message = null)
    }

}
