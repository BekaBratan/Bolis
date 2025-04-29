package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class ProfileUpdateResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("message")
    val message: String
)