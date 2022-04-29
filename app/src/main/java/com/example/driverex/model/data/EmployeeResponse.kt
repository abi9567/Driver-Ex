package com.example.driverex.model.data

    data class EmployeeResponse(
        val current_page: Int,
        val `data`: List<EmployeeData>,
        val first_page_url: String,
        val from: Int,
        val last_page: Int,
        val last_page_url: String,
        val links: List<Link>,
        val next_page_url: String,
        val path: String,
        val per_page: Int,
        val prev_page_url: Any,
        val to: Int,
        val total: Int
    )
        data class EmployeeData(
            val created_at: String,
            val date_of_birth: String,
            val designation_id: Int,
            val email: String,
            val first_name: String,
            val gender: String,
            val id: Int,
            val join_date: String,
            val landline: Long,
            val last_name: String,
            val mobile: Long,
            val permanent_address: String,
            val present_address: String,
            val profile_picture: String,
            val resume: String,
            val status: String,
            val updated_at: String
        )

        data class Link(
            val active: Boolean,
            val label: String,
            val url: Any
        )

