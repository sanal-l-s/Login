package com.example.login.model

import com.google.gson.annotations.SerializedName

data class Credential(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String

)

