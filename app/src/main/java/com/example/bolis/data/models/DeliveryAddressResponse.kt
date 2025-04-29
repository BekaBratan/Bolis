package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class DeliveryAddressResponse(
    @SerializedName("address_line")
    val addressLine: String,
    @SerializedName("city")
    val city: String
)