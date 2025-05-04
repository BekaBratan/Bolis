package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class LikedItem(
    @SerializedName("item_id")
    val itemId: Int?
)