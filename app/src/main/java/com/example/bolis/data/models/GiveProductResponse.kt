package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class GiveProductResponse(
    @SerializedName("item_id")
    val itemId: Int,
    @SerializedName("message")
    val message: String
)