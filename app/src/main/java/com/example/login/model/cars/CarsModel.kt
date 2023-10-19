package com.example.login.model.cars


import com.google.gson.annotations.SerializedName

data class CarsModel(
    @SerializedName("Count")
    val count: Int? = null,
    @SerializedName("Message")
    val message: String? = null,
    @SerializedName("Results")
    val results: List<Result?>? = null,
    @SerializedName("SearchCriteria")
    val searchCriteria: Any? = null
)