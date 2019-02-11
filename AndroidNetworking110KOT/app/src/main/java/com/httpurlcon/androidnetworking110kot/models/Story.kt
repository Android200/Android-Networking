package com.httpurlcon.androidnetworking110kot.models

import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("genre")
    val genre: List<String>,
    @SerializedName("image")
    val image: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("releaseYear")
    val releaseYear: Int,
    @SerializedName("title")
    val title: String
)