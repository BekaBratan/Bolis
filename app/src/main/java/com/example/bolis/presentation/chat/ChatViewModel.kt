package com.example.bolis.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bolis.data.api.ChatRequest
import com.example.bolis.data.api.ChatResponse
import com.example.bolis.data.api.Message
import com.example.bolis.data.api.RetrofitClient
import com.example.bolis.utils.Constants.Companion.OPENAI_API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel : ViewModel() {
    var messages by mutableStateOf<List<Message>>(listOf())

    fun sendMessage(userInput: String) {
        val updatedMessages = messages + Message("user", userInput)
        messages = updatedMessages

        val request = ChatRequest(
            messages = updatedMessages
        )

        RetrofitClient.api.chatCompletion(request, "Bearer $OPENAI_API_KEY")
            .enqueue(object : Callback<ChatResponse> {
                override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                    response.body()?.choices?.firstOrNull()?.message?.let { reply ->
                        messages = messages + reply
                    }
                }

                override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                    // Handle error
                }
            })
    }
}