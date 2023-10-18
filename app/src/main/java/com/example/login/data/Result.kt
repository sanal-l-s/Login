package com.example.login.data


import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeString(mfrCommonName)
        parcel.writeValue(mfrID)
        parcel.writeString(mfrName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}