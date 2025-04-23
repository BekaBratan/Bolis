package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class LogInResponse(
    @SerializedName("redirect")
    val redirect: String,
    @SerializedName("success")
    val success: String,
    @SerializedName("token")
    val token: String
)