package com.example.login.data


import com.google.gson.annotations.SerializedName

data class VehicleType(
    @SerializedName("IsPrimary")
    val isPrimary: Boolean? = null,
    @SerializedName("Name")
    val name: String? = null
)