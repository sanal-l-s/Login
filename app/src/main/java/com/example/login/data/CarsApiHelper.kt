package com.example.login.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CarsApiHelper {
    private const val baseUrl = "https://vpic.nhtsa.dot.gov/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}