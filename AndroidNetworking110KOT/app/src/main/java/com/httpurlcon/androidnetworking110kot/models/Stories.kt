package com.httpurlcon.androidnetworking110kot.models

import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("Stories")
    val stories: List<Story>
)