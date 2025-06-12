package com.example.bolis.data.models

data class TogetherAIChatRequest(
    val model: String = "deepseek-ai/DeepSeek-V3",
    val messages: List<TogetherAIMessage>
)

data class TogetherAIMessage(
    val role: String, // "user" or "assistant"
    val content: String
)

data class TogetherAIChatResponse(
    val choices: List<TogetherAIChoice>
)

data class TogetherAIChoice(
    val message: TogetherAIMessage
)