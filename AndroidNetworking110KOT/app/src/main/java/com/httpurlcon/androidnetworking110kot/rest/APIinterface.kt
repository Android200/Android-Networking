package com.httpurlcon.androidnetworking110kot.rest

import com.httpurlcon.androidnetworking110kot.models.Stories
import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("movie.json")
    fun getMoviees(): Call<Stories>
}