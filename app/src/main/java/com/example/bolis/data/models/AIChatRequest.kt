package com.example.bolis.data.models

// ChatRequest.kt
data class AIChatRequest(
    val model: String = "gpt-3.5-turbo",
    val AIMessages: List<AIMessage>
)

data class AIMessage(
    val role: String, // "user" or "assistant"
    val content: String
)

// ChatResponse.kt
data class AIChatResponse(
    val AIChoices: List<AIChoice>
)

data class AIChoice(
    val AIMessage: AIMessage
)

data class ChatRequest(
    val to: Int? = 0,
    val content: String? = "null",
    val type: String? = "text",
)

data class ChatResponse(
    val from: Int? = 0,
    val to: Int? = 0,
    val content: String? = "null",
    val timestamp: String? = "null",
    val is_me: String? = "null",
    val is_read: Boolean? = false
)

data class ChatListResponse(
    val messages: List<ChatResponse>
)

