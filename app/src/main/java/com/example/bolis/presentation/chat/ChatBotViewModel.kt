package com.example.bolis.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ChatGptApi
import com.example.bolis.data.api.ServiceBuilderGpt
import com.example.bolis.data.models.ChatRequest
import com.example.bolis.data.models.Message
import com.example.bolis.utils.Constants
import kotlinx.coroutines.launch

class ChatBotViewModel : ViewModel() {

    private val chatApi = ServiceBuilderGpt.buildService(ChatGptApi::class.java)

    private val _botMessage = MutableLiveData<String>()
    val botMessage: LiveData<String> = _botMessage

    fun sendMessageToBot(userInput: String) {
        viewModelScope.launch {
            try {
                val systemMessage = Message(
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

                val userMessage = Message(role = "user", content = userInput)

                val request = ChatRequest(
                    model = "gpt-3.5-turbo",
                    messages = listOf(systemMessage, userMessage)
                )


                val response = chatApi.sendMessage(request, "Bearer ${Constants.ChatGPTApi}")
                val reply = response.choices.firstOrNull()?.message?.content ?: "No response"
                _botMessage.postValue(reply)

            } catch (e: Exception) {
                _botMessage.postValue("Error: ${e.message}")
            }
        }
    }

}

