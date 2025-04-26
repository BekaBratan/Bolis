package com.example.bolis.data.models


import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Multipart

data class GiveProductRequest(
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("name")
    val name: String
)