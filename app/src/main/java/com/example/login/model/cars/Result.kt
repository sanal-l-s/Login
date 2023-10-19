package com.example.login.model.cars


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable