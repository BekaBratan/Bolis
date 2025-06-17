package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("author")
    val author: Author? = Author(),
    @SerializedName("content")
    val content: String? = "null",
    @SerializedName("created_at")
    val createdAt: String? = "null",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "null",
    @SerializedName("user_id")
    val userId: Int? = 0,
)