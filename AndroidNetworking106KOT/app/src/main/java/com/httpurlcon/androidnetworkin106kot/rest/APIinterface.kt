package com.httpurlcon.androidnetworkin106kot.rest

import com.httpurlcon.androidnetworkin106kot.model.TennisResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("JSON1.json")
    fun getTennisPlayers(): Call<TennisResponse>
}