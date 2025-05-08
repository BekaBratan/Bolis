package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class MyGivesResponse(
    @SerializedName("items")
    val items: List<ItemXX>?
)