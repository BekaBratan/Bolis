package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class UpdatePasswordRequest(
    @SerializedName("confirm_password")
    val confirmPassword: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("new_password")
    val newPassword: String
)