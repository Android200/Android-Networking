package com.httpurlcon.androidnetworking105kot.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("items")
    @Expose
    var items: List<Item>)
