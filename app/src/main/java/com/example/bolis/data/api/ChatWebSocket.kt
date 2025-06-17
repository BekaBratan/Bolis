package com.example.bolis.data.api

import com.example.bolis.data.models.ChatResponse
import com.example.bolis.utils.Constants.Companion.CHAT_URL
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ChatService(private val token: String) {
    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null
    var onNewMessage: ((ChatResponse) -> Unit)? = null

    fun connectWebSocket() {
        val request = Request.Builder()
            .url(CHAT_URL)
            .addHeader("Authorization", "$token")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(ws: WebSocket, response: Response) {
                println("✅ WebSocket connected")
            }

            override fun onMessage(ws: WebSocket, text: String) {
                try {
                    val jsonObject = JSONObject(text)
                    val messageResponse = ChatResponse(
                        from = jsonObject.optInt("from"),
                        to = jsonObject.optInt("to"),
                        content = jsonObject.optString("content"),
                        timestamp = jsonObject.optString("timestamp"),
                        is_me = jsonObject.optString("is_me"),
                        is_read = jsonObject.optBoolean("is_read")
                    )
                    println("📩 Received: $messageResponse")
                    onNewMessage?.invoke(messageResponse)
                } catch (e: Exception) {
                    println("❌ Error parsing message: ${e.message}")
                }
            }

            override fun onFailure(ws: WebSocket, t: Throwable, response: Response?) {
                println("❌ Error: ${t.message}")
            }
        })
    }

    fun sendMessage(toUserId: Int, content: String) {
        val message = JSONObject().apply {
            put("to", toUserId)
            put("content", content)
            put("type", "message")
        }

        webSocket?.send(message.toString())
    }
}