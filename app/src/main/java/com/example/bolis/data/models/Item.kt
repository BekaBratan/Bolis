package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("average_rating")
    val averageRating: Int = 0,
    @SerializedName("category")
    val category: Any? = "",
    @SerializedName("category_id")
    val categoryId: Int = 75,
    @SerializedName("condition")
    val condition: String = "New",
    @SerializedName("CreatedAt")
    val createdAt: String = "2023-10-01T12:00:00Z",
    @SerializedName("DeletedAt")
    val deletedAt: Any? = null,
    @SerializedName("description")
    val description: String = "Sample description",
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("image_path")
    val imagePath: String = "https://example.com/image.jpg",
    @SerializedName("Images")
    val images: List<Image> = listOf(
        Image(
            createdAt = "2023-10-01T12:00:00Z",
            deletedAt = null,
            iD = 1,
            imagePath = "https://example.com/image1.jpg",
            itemId = 0,
            updatedAt = "2023-10-01T12:00:00Z"
        )
    ),
    @SerializedName("is_available")
    val isAvailable: Boolean = true,
    @SerializedName("name")
    val name: String = "Sample Item",
    @SerializedName("OrderItems")
    val orderItems: Any? = null,
    @SerializedName("price")
    val price: Int = 100,
    @SerializedName("review_count")
    val reviewCount: Int = 0,
    @SerializedName("seller_id")
    val sellerId: Int = 0,
    @SerializedName("UpdatedAt")
    val updatedAt: String = "2023-10-01T12:00:00Z"
)