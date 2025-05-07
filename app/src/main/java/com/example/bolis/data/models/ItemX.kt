package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("average_rating")
    val averageRating: Int?,
    @SerializedName("category")
    val category: Any?,
    @SerializedName("category_id")
    val categoryId: Int?,
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("CreatedAt")
    val createdAt: String?,
    @SerializedName("DeletedAt")
    val deletedAt: Any?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("ID")
    val iD: Int?,
    @SerializedName("image_path")
    val imagePath: String?,
    @SerializedName("Images")
    val images: Any?,
    @SerializedName("is_available")
    val isAvailable: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("OrderItems")
    val orderItems: Any?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("review_count")
    val reviewCount: Int?,
    @SerializedName("seller_id")
    val sellerId: Int?,
    @SerializedName("UpdatedAt")
    val updatedAt: String?
)