package com.example.login.data

import retrofit2.Response
import retrofit2.http.GET

interface QuotesApiService {
    @GET("quotes/")
    suspend fun getRandomQuote(): Response<List<String>>
}