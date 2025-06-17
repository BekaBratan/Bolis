package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("posts")
    val posts: List<Post?>? = emptyList(),
)