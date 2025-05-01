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
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
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
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.data.models.Message
import com.example.bolis.ui.Elements.ChatTextView
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ChatBotScreen(
    backButtonClicked:() -> Unit = {},
    viewModel: ChatBotViewModel = viewModel(),
) {
    val chatMessages = remember { mutableStateListOf<Message>() }
    var userInput by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val context = LocalContext.current
    navBarStateChange(false)


    // Observe bot replies
    LaunchedEffect(viewModel.botMessage) {
        viewModel.botMessage.observeForever { botReply ->
            chatMessages.add(Message(content = botReply, role = "bot"))
        }
    }

    Box(
        modifier = Modifier
            .padding(top = 28.dp, start = 24.dp)
            .fillMaxWidth(),
    ) {
        CustomBackButton(
            name = "Back"
        ) { backButtonClicked() }
        androidx.compose.material3.Text(
            text = "Bolis consultant",
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
            modifier = Modifier.fillMaxWidth().weight(1f),
            state = listState,
            verticalArrangement = Arrangement.Bottom
        ) {
            items(chatMessages) { message ->
                if (message.role == "user") {
                    ChatTextView(text = message.content, isMine = true)
                } else {
                    ChatTextView(text = message.content, isMine = false)
                }
            }
        }

        LaunchedEffect(chatMessages.size) {
            if (chatMessages.isNotEmpty()) {
                listState.scrollToItem(chatMessages.size - 1)
            }
        }

        // Quick Replies
        Row (
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Hello", "How can I donate?", "Need help").forEach { text ->
                AssistChip(text = text, onClick = { userInput = text })
            }
        }

        // Input field and send button
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
                            androidx.compose.material3.Text(
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
                    .background(Green50)
                    .padding(16.dp)
                    .clickable(onClick = {
                        if (userInput.isNotEmpty()) {
                            chatMessages.add(Message(role = "user", content = userInput))
                            viewModel.sendMessageToBot(userInput)
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

@Composable
fun AssistChip(text: String, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick, shape = RoundedCornerShape(50)) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = Grey30
            )
        )
    }
}
