package com.httpurlcon.androidnetworking107kot.rest

import com.httpurlcon.androidnetworking107kot.mdel.contacts
import com.httpurlcon.androidnetworking107kot.mdel.contactsResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("JSON2.json")
    fun getContact(): Call<contactsResponse>
}