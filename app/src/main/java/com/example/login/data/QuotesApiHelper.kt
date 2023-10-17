package com.example.login.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuotesApiHelper {
    private const val baseUrl = "https://ron-swanson-quotes.herokuapp.com/v2/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
