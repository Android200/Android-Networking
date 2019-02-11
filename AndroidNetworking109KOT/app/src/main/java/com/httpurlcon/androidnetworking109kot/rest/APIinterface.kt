package com.httpurlcon.androidnetworking109kot.rest

import com.httpurlcon.androidnetworking109kot.models.Movie
import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("JSON4.json")
    fun getMovies(): Call<Movie>
}