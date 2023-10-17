package com.example.login.data


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("Country")
    val country: String? = null,
    @SerializedName("Mfr_CommonName")
    val mfrCommonName: String? = null,
    @SerializedName("Mfr_ID")
    val mfrID: Int? = null,
    @SerializedName("Mfr_Name")
    val mfrName: String? = null,
    @SerializedName("VehicleTypes")
    val vehicleTypes: List<VehicleType?>? = null
)