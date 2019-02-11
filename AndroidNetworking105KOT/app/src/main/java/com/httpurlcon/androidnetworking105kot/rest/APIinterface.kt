package com.httpurlcon.androidnetworking105kot.rest

import com.httpurlcon.androidnetworking105kot.model.ItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("/search/users?q=language:java+location:kano")
    fun getDevsKano() : Call<ItemResponse>
}