package com.httpurlcon.androidnetworking109kot.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object {
        val baseURL: String = "https://auna.000webhostapp.com/apikot/"
        var retrofit: Retrofit? = null

        val client: Retrofit
        get() {
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                        .baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit!!
        }
    }
}