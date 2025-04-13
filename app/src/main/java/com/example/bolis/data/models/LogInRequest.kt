package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class LogInRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)