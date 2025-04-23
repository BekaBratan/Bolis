package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("username")
    val username: String
)