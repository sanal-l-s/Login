package com.example.login.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPiService {
    @POST("auth/login")
    suspend fun login(@Body credential: Credential): Response<UserData>

}