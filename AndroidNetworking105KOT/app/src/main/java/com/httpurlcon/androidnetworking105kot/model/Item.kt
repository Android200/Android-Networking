package com.httpurlcon.androidnetworking105kot.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("login")
    val login: String
)