package com.example.bolis.ui.Elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CustomHugeTextField(
    name: String = "",
    isRequired: Boolean = false,
    placeholder: String = ""
) {
    val (text, setText) = remember { mutableStateOf("") }
    val isRequired = isRequired

    Box {
        if (name.isNotEmpty())
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = if (isRequired) "$name*" else name,
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                fontFamily = fontFamily,
                color = Green50
            )

        BasicTextField(
            value = text,
            onValueChange = setText,
            keyboardOptions = KeyboardOptions(keyboardType = if (name == "Phone number") KeyboardType.Phone else KeyboardType.Text),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                fontFamily = fontFamily,
                color = Color.Black,
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = fontFamily,
                                color = Grey20
                            )
                        )
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
                .height(238.dp)
                .border(1.dp, Green50, RoundedCornerShape(7.dp)),
        )
    }
}