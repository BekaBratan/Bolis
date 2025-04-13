package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("message")
    val message: String
)