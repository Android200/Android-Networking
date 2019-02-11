package com.httpurlcon.androidnetworking108kot.model

import com.google.gson.annotations.SerializedName

data class Phonebook(
    @SerializedName("Payload")
    val payload: List<Payload>
)