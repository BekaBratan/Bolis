package com.example.bolis.data.api

import com.example.bolis.data.models.AIChatRequest
import com.example.bolis.data.models.AIChatResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChatGptApi {
    @POST("v1/chat/completions")
    suspend fun sendMessage(
        @Body request: AIChatRequest,
        @Header("Authorization") authHeader: String
    ): AIChatResponse
}
