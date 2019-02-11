package com.httpurlcon.androidnetworkin106kot.model

import com.google.gson.annotations.SerializedName

data class Tennis(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("imgURL")
    val imgURL: String,
    @SerializedName("name")
    val name: String
)