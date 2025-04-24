package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class CategoriesListResponse(
    @SerializedName("categories")
    val categories: List<Category>
)