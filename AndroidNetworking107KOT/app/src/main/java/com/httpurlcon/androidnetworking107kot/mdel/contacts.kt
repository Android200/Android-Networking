package com.httpurlcon.androidnetworking107kot.mdel

import com.google.gson.annotations.SerializedName

data class contacts(
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: Phone
)