package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("search_query")
    val searchQuery: String,
    @SerializedName("suggestions")
    val suggestions: Any?
)