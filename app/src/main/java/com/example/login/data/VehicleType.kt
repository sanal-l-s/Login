package com.example.login.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleType(
    @SerializedName("IsPrimary")
    val isPrimary: Boolean? = null,
    @SerializedName("Name")
    val name: String? = null
) : Parcelable