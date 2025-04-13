package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ChatTextView(
    text: String = "TextField",
    isMine: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = if (isMine) Arrangement.End else Arrangement.Start
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = if (isMine) White50 else Black40,
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = if (isMine) 15.dp else 0.dp, bottomEnd = if (isMine) 0.dp else 15.dp))
                .background(
                    if (isMine)
                        Green50
                    else
                        White40,
                )
                .padding(horizontal = 22.dp, vertical = 6.dp),
        )
    }

}