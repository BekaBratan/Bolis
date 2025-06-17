package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("avatar")
    val avatar: String? = "null",
    @SerializedName("first_name")
    val firstName: String? = "null",
    @SerializedName("last_name")
    val lastName: String? = "null"
)