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

