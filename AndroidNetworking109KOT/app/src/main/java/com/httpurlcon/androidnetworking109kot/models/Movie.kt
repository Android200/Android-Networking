package com.httpurlcon.androidnetworking109kot.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Movies")
    val movies: List<Movy>
)