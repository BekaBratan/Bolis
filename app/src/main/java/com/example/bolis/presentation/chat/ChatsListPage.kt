package com.example.bolis.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.R
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.ui.Elements.ChatsListItem
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ChatsListPage(
    viewModel: ChatBotViewModel = viewModel(),
    chatOpen:(chatID: Int) -> Unit = {}
) {
    navBarStateChange(true)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Chats list",
            fontSize = 17.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50,
            modifier = Modifier.fillMaxWidth()
        )

        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(White50)
                .weight(1f)
        ) {
            ChatsListItem(
                iconId = R.drawable.ic_chat_consultant,
                chatName = "Bolis consultant",
                chatClicked = { chatOpen(0) },
                noteCount = 0
            )

            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grey30))

            ChatsListItem(
                iconId = R.drawable.ic_chat_gives,
                chatName = "My gives",
                chatClicked = { chatOpen(1) },
                noteCount = 0
            )

            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grey30))

            ChatsListItem(
                iconId = R.drawable.ic_chat_admin,
                chatName = "Admin",
                chatClicked = { chatOpen(2) },
                noteCount = 0
            )

            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grey30))

            ChatsListItem(
                iconId = R.drawable.ic_chat_budget,
                chatName = "Badges",
                chatClicked = { chatOpen(3) },
                noteCount = 0
            )

            Spacer(Modifier.fillMaxWidth().height(1.dp).background(Grey30))
        }
    }
}