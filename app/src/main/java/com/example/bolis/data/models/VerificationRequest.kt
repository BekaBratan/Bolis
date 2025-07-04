package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class VerificationRequest(
    @SerializedName("code")
    val code: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("flow")
    val flow: String,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null
)