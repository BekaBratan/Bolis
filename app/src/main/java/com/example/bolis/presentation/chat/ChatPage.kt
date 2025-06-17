package com.example.bolis.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.R
import com.example.bolis.data.api.ChatService
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.data.models.AIMessage
import com.example.bolis.data.models.ChatRequest
import com.example.bolis.data.models.ChatResponse
import com.example.bolis.ui.Elements.ChatTextView
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green30
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun ChatPage(
    backButtonClicked:() -> Unit = {},
    viewModel: ChatBotViewModel = viewModel(),
    chatID: Int = 1,
) {
    navBarStateChange(false)
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)

    val chatMessages = remember { mutableStateListOf<ChatResponse>() }
    val listState = rememberLazyListState()
    var userInput by remember { mutableStateOf("") }

    val chatService = remember {
        ChatService(
            token = sharedProvider.getToken()
        )
    }

    LaunchedEffect(true) {
        chatService.connectWebSocket()
        chatService.onNewMessage = { newMessage ->
            chatMessages.add(
                newMessage
            )
        }

        viewModel.getChatHistory(sharedProvider.getToken())

        viewModel.chatResponse.observeForever { chatHistory ->
            chatHistory?.messages?.let { messages ->
                chatMessages.addAll(messages)
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(top = 28.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
    ) {
        CustomBackButton(
            name = "Back"
        ) { backButtonClicked() }
        Text(
            text = "Chat",
            fontSize = 17.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = listState,
            verticalArrangement = Arrangement.Bottom
        ) {
            val groupedMessages = chatMessages.groupBy { message ->
                // Assuming `timestamp` is in a parsable date format
                message.timestamp?.substring(0, 10) // Extract the date part (e.g., "YYYY-MM-DD")
            }

            groupedMessages.forEach { (date, messages) ->
                item {
                    Text(
                        text = date ?: "Unknown Date",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Grey30,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }

                items(messages) { message ->
                    if (message.is_me == "true") {
                        ChatTextView(
                            text = message.content.toString(),
                            isMine = true,
                            time = message.timestamp?.substring(11, 16) ?: "00:00" // Extract time part (e.g., "HH:MM"
                        )
                    } else {
                        ChatTextView(
                            text = message.content.toString(),
                            isMine = false,
                            time = message.timestamp?.substring(11, 16) ?: "00:00" // Extract time part (e.g., "HH:MM"
                        )
                    }
                }
            }
        }

        LaunchedEffect(chatMessages.size) {
            if (chatMessages.isNotEmpty()) {
                listState.scrollToItem(chatMessages.size - 1)
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = userInput,
                onValueChange = { userInput = it },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = fontFamily,
                    color = Color.Black,
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        if (userInput.isEmpty()) {
                            Text(
                                text = "Type your messages...",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    fontFamily = fontFamily,
                                    color = Grey30
                                )
                            )
                        }
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(53.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(White40),
            )
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(Green30)
                    .padding(16.dp)
                    .clickable(onClick = {
                        if (userInput.isNotEmpty()) {
                            val message = ChatRequest(
                                to = 8,
                                content = userInput,
                                type = "message"
                            )
                            chatService.sendMessage(message.to!!, message.content!!)
                            userInput = ""
                        }
                        userInput = ""
                    }),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "send",
                    tint = White50
                )
            }
        }
    }
}