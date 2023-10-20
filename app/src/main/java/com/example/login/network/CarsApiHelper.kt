package com.example.login.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CarsApiHelper {
    private const val baseUrl = "https://vpic.nhtsa.dot.gov/api/"

    val api: CarsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarsApiService::class.java)
    }

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}