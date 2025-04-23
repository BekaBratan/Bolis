package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class VerificationResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("next")
    val next: String
)