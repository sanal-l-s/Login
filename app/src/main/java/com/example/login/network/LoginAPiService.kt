package com.example.login.network

import com.example.login.model.Credential
import com.example.login.model.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPiService {
    @POST("auth/login")
    suspend fun login(@Body credential: Credential): Response<UserData>

}