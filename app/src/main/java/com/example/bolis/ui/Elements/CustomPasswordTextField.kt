package com.example.bolis.ui.Elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CustomPasswordTextField(name: String = "PassTextField", isRequired: Boolean = false) {
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val isRequired = isRequired

    Box {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = if (isRequired) "$name*" else name,
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            fontFamily = fontFamily,
            color = Green50
        )

        BasicTextField(
            value = password,
            onValueChange = setPassword,
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                fontFamily = fontFamily,
                color = Color.Black,
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(Modifier.weight(1f)) { innerTextField() }
                    val image = if (passwordVisible) R.drawable.ic_show else R.drawable.ic_show
                    Image(
                        painter = painterResource(image),
                        contentScale = ContentScale.Fit,
                        contentDescription = "show",
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .clickable(onClick = {
                                setPasswordVisible(!passwordVisible)
                            })
                    )
                }
            },
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth()
                .height(40.dp)
                .border(1.dp, Green50, RoundedCornerShape(7.dp)),
        )
    }
}