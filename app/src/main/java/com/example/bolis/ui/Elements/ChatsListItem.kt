package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Grey50
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ChatsListItem(
    chatClicked:() -> Unit = {},
    chatName:String = "Bolis consultant",
    iconId:Int = R.drawable.ic_chat_consultant,
    noteCount:Int = 4
)  {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(White50)
            .padding(16.dp)
            .clickable(onClick = chatClicked)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, Grey50, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(iconId),
                contentScale = ContentScale.Crop,
                contentDescription = "chat",
            )
        }

        Text(
            text = chatName,
            fontSize = 17.sp,
            fontWeight = FontWeight(500),
            fontFamily = fontFamily,
            color = Black50,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )

        if (noteCount > 0) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Red50)
                    .padding(vertical = 5.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$noteCount",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    fontFamily = fontFamily,
                    color = White50
                )
            }
        }
    }
}