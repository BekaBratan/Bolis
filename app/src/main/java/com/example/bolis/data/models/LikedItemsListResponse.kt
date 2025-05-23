package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class LikedItemsListResponse(
    @SerializedName("liked_items")
    val likedItems: List<LikedItem?>?
)