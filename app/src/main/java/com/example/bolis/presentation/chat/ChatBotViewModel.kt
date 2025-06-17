package com.example.bolis.presentation.chat

import android.util.JsonToken
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ChatGptApi
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.api.ServiceBuilderGpt
import com.example.bolis.data.api.TogetherServiceBuilder
import com.example.bolis.data.models.AIChatRequest
import com.example.bolis.data.models.AIMessage
import com.example.bolis.data.models.ChatListResponse
import com.example.bolis.data.models.ErrorResponse
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.TogetherAIChatRequest
import com.example.bolis.data.models.TogetherAIMessage
import com.example.bolis.data.models.VerificationResponse
import com.example.bolis.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatBotViewModel : ViewModel() {

    private var _chatResponse: MutableLiveData<ChatListResponse?> = MutableLiveData()
    val chatResponse: LiveData<ChatListResponse?> = _chatResponse

    private var _errorResponse: MutableLiveData<String?> = MutableLiveData()
    val errorResponse: LiveData<String?> = _errorResponse

    private var _errorMessageResponse: MutableLiveData<ErrorResponse?> = MutableLiveData()
    val errorMessageResponse: LiveData<ErrorResponse?> = _errorMessageResponse

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    fun sendMessage(userMessage: String) {
        viewModelScope.launch {
            val systemAIMessage = TogetherAIMessage(
                role = "system",
                content = """
                    Ты — виртуальный помощник Bôlis, платформы для онлайн-благотворительности, которая помогает людям делиться ненужными вещами и находить то, что им нужно.
Твоя задача — помогать пользователям с вопросами о регистрации, подаче объявлений, поиске предметов, работе приложения и сайта, а также давать советы по использованию платформы.
Отвечай дружелюбно, понятно и кратко, как будто разговариваешь с другом.
Если не знаешь ответа — предложи обратиться в поддержку по email bolis.help@gmail.com или телефону +7 747 700 6423.
Используй казахский, русский или английский язык в зависимости от запроса пользователя.
Если сообщение содержит несколько языков, выбери основной или уточни у пользователя.
Не используй слишком формальный стиль, избегай сложных терминов — объясняй просто и доступно.
                """.trimIndent()
            )

            val request = TogetherAIChatRequest(
                messages = listOf(
                    systemAIMessage,
                    TogetherAIMessage("user", userMessage)
                )
            )

            try {
                val reply = TogetherServiceBuilder.apiService.getCompletion(request)
                _response.value = reply.choices.firstOrNull()?.message?.content ?: "No response"
            } catch (e: Exception) {
                _response.value = "Error: ${e.message}"
            }
        }
    }

    fun getChatHistory(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getMessages(token)
            }.fold(
                onSuccess = {
                    _chatResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? retrofit2.HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }
}

