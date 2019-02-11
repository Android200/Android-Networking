package com.httpurlcon.androidnetworkin106kot.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TennisResponse(
    @SerializedName("data")
    @Expose
    var  data: List<Tennis>
)