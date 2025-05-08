package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class ItemDetailsResponse(
    @SerializedName("averageRating")
    val averageRating: Int?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("item")
    val item: ItemX?,
    @SerializedName("reviewCount")
    val reviewCount: Int?,
    @SerializedName("reviews")
    val reviews: List<Any?>?
)