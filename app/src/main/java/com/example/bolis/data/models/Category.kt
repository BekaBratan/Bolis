package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("CreatedAt")
    val createdAt: String,
    @SerializedName("DeletedAt")
    val deletedAt: Any?,
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("Products")
    val products: Any?,
    @SerializedName("UpdatedAt")
    val updatedAt: String
)