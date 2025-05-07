package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class ImageX(
    @SerializedName("CreatedAt")
    val createdAt: String?,
    @SerializedName("DeletedAt")
    val deletedAt: Any?,
    @SerializedName("ID")
    val iD: Int?,
    @SerializedName("image_path")
    val imagePath: String?,
    @SerializedName("item_id")
    val itemId: Int?,
    @SerializedName("UpdatedAt")
    val updatedAt: String?
)