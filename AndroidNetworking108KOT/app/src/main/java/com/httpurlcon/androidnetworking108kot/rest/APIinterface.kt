package com.httpurlcon.androidnetworking108kot.rest

import com.httpurlcon.androidnetworking108kot.model.Phonebook
import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("JSON3.json")
    fun getPhonebook(): Call<Phonebook>
}