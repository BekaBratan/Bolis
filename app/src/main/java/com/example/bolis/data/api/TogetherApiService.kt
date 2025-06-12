package com.example.bolis.data.api

import com.example.bolis.data.models.TogetherAIChatRequest
import com.example.bolis.data.models.TogetherAIChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TogetherApiService {
    @POST("v1/chat/completions")
    suspend fun getCompletion(
        @Body request: TogetherAIChatRequest,
    ): TogetherAIChatResponse
}