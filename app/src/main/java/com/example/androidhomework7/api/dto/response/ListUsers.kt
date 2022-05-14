package com.example.androidhomework7.api.dto.response

import com.google.gson.annotations.SerializedName

data class ListUsers(

    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    val data: List<DataItem>,
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val support: Support
)

data class DataItem(

    val id: Long,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String

)

data class Support(

    val text: String, val url: String
)
