package com.httpurlcon.androidnetworking108kot.model

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("Address")
    val address: String,
    @SerializedName("Avatar")
    val avatar: String,
    @SerializedName("DOB")
    val dOB: String,
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("Phone")
    val phone: Phone
)