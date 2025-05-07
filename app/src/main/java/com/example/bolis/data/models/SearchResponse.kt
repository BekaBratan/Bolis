package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    val items: List<Item>?,
    @SerializedName("query")
    val query: String?
)