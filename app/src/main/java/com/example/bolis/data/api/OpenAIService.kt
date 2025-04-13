package com.example.bolis.data.api

import com.example.bolis.utils.Constants.Companion.OPENAI_API_KEY
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIService {
    @POST("v1/chat/completions")
    fun chatCompletion(
        @Body request: ChatRequest,
        @Header("Authorization") auth: String = "Bearer $OPENAI_API_KEY"
    ): Call<ChatResponse>
}

data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: OpenAIService = retrofit.create(OpenAIService::class.java)
}