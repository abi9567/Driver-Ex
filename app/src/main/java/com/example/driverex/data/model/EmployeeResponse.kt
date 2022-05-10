package com.example.driverex.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

    @Parcelize
    data class EmployeeResponse(
        @SerializedName("employeeData")
        val `data`: List<EmployeeData>,
    ) : Parcelable

        @Parcelize
        data class EmployeeData(
            @SerializedName("email")
            val email: String?,
            @SerializedName("firstName")
            val first_name: String?,
            @SerializedName("landLineNumber")
            val landline: Long?,
            @SerializedName("lastName")
            val last_name: String?,
            @SerializedName("mobileNumber")
            val mobile: Long?,
            @SerializedName("profilePhoto")
            val profile_picture: String?,
        ) : Parcelable


data class EmployeeErrorResponse
    (
    val message : String
)
