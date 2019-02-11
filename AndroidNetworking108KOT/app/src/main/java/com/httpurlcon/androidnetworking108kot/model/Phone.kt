package com.httpurlcon.androidnetworking108kot.model

import com.google.gson.annotations.SerializedName

data class Phone(
    @SerializedName("Home")
    val home: String,
    @SerializedName("Mobile")
    val mobile: String,
    @SerializedName("Office")
    val office: String
)