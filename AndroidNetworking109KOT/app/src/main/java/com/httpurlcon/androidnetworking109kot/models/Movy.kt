package com.httpurlcon.androidnetworking109kot.models

import com.google.gson.annotations.SerializedName

data class Movy(
    @SerializedName("name")
    val name: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("url")
    val url: Url
)