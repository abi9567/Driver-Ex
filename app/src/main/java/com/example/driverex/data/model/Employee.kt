package com.example.driverex.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

        @Parcelize
        data class Employee
            (
            @SerializedName("email")
            val email: String?,

            @SerializedName("first_name")
            val firstName: String?,

            @SerializedName("landline")
            val landLineNumber: Long?,

            @SerializedName("last_name")
            val lastName: String?,

            @SerializedName("mobile")
            val mobileNumber: Long?,

            @SerializedName("profile_picture")
            val profilePhoto: String?,

            ) : Parcelable
