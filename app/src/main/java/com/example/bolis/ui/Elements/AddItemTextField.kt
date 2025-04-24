package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun AddItemTextField(
    name: String = "TextField",
    isRequired: Boolean = false,
    placeholder: String = "",
    isError: Boolean = false,
    multiLine: Boolean = false,
    text: String = "",
    setText: (String) -> Unit = {},
) {
    val isRequired = isRequired
    val height = if (multiLine) 96.dp else 53.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = if (isRequired) "$name*" else name,
            fontSize = 15.sp,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = Black40
        )

        BasicTextField(
            value = text,
            onValueChange = setText,
            singleLine = !multiLine,
            keyboardOptions = KeyboardOptions(keyboardType = if (name == "Phone number") KeyboardType.Phone else KeyboardType.Text),
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
                        .verticalScroll(rememberScrollState(), reverseScrolling = multiLine)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Grey30
                            )
                        )
                    }
                    if (multiLine)
                        Text(
                            text = text,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Black40,
                            )
                        )
                    else
                        innerTextField()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(White40)
                .border(
                    width = if (isError) 1.dp else 0.dp,
                    color = if (isError) Red50 else Color.Transparent,
                    shape = RoundedCornerShape(15.dp)
                ),
        )
    }
}