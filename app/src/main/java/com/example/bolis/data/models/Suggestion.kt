package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class Suggestion(
    @SerializedName("average_rating")
    val averageRating: Int = 0,
    @SerializedName("category")
    val category: Any? = null,
    @SerializedName("category_id")
    val categoryId: Int = 0,
    @SerializedName("condition")
    val condition: String = "",
    @SerializedName("CreatedAt")
    val createdAt: String = "",
    @SerializedName("DeletedAt")
    val deletedAt: Any? = null,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("image_path")
    val imagePath: String = "",
    @SerializedName("Images")
    val images: Any? = null,
    @SerializedName("is_available")
    val isAvailable: Boolean = false,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("OrderItems")
    val orderItems: Any? = null,
    @SerializedName("price")
    val price: Int = 0,
    @SerializedName("review_count")
    val reviewCount: Int = 0,
    @SerializedName("seller_id")
    val sellerId: Int = 0,
    @SerializedName("UpdatedAt")
    val updatedAt: String = "",
)