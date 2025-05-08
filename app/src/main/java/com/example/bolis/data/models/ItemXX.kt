package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("average_rating")
    val averageRating: Int? = null,
    @SerializedName("category")
    val category: Any? = null,
    @SerializedName("category_id")
    val categoryId: Int? = null,
    @SerializedName("condition")
    val condition: String? = null,
    @SerializedName("CreatedAt")
    val createdAt: String? = null,
    @SerializedName("DeletedAt")
    val deletedAt: Any? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("ID")
    val iD: Int? = null,
    @SerializedName("image_path")
    val imagePath: String? = null,
    @SerializedName("Images")
    val images: List<Image>? = emptyList(),
    @SerializedName("is_available")
    val isAvailable: Boolean? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("OrderItems")
    val orderItems: Any? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("review_count")
    val reviewCount: Int? = null,
    @SerializedName("seller_id")
    val sellerId: Int? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("UpdatedAt")
    val updatedAt: String? = null
)