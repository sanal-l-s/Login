package com.example.login.network

import retrofit2.Response
import retrofit2.http.GET

interface QuotesApiService {
    @GET("quotes/")
    suspend fun getRandomQuote(): Response<List<String>>
}