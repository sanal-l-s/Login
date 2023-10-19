package com.example.login.network

import com.example.login.model.cars.CarsModel
import retrofit2.Response
import retrofit2.http.GET

interface CarsApiService {
    @GET("vehicles/getallmanufacturers?format=json")
    suspend fun getCars(): Response<CarsModel>

}