package com.example.login.data

import retrofit2.Response
import retrofit2.http.GET

interface CarsApiService {
    @GET("vehicles/getallmanufacturers?format=json")
    suspend fun getCars(): Response<CarsModel>

}