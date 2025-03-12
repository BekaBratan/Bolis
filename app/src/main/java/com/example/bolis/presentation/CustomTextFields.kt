package com.example.bolis.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.fontFamily


@Preview
@Composable
fun CustomTextField(name: String = "TextField", isRequired: Boolean = false, placeholder: String = "") {
    val (text, setText) = remember { mutableStateOf("") }
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
            value = text,
            onValueChange = setText,
            singleLine = true,
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
                        .padding(horizontal = 14.dp),
                    contentAlignment = Alignment.CenterStart
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
                .height(40.dp)
                .border(1.dp, Green50, RoundedCornerShape(7.dp)),
        )
    }
}

@Preview
@Composable
fun CustomDropdown(
    name: String = "City",
    isRequired: Boolean = false,
    options: List<String> = listOf<String>("Almaty", "Astana", "Shymkent"),
) {
    var text by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var tempOptions by remember { mutableStateOf(options) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = if (isRequired) "$name*" else name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily,
            color = Green50
        )

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                tempOptions = options.filter { option -> option.contains(text, ignoreCase = true) }
            },
            singleLine = true,
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
                    val image = if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                    Image(
                        painter = painterResource(image),
                        contentScale = ContentScale.Fit,
                        contentDescription = "show",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = {
                                if (!tempOptions.isEmpty())
                                    expanded = !expanded
                            })
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .border(1.dp, Green50, RoundedCornerShape(7.dp)),
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            tempOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        text = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomCodeTextField() {
    val (text, setText) = remember { mutableStateOf("") }

    Box {
        val focusRequesters = List(4) { FocusRequester() }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (i in 0 until 4) {
                BasicTextField(
                    value = text.getOrNull(i)?.toString() ?: "",
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            val newText = text.toMutableList()
                            if (newValue.isNotEmpty()) {
                                if (i < text.length) {
                                    newText[i] = newValue[0]
                                } else {
                                    newText.add(newValue[0])
                                }
                                if (i < 3) {
                                    focusRequesters[i + 1].requestFocus()
                                }
                            } else if (i < text.length) {
                                newText.removeAt(i)
                                if (i > 0) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                            }
                            setText(newText.joinToString(""))
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 57.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = fontFamily,
                        color = Black20,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(72.dp)
                        .border(2.dp, Green50, RoundedCornerShape(20.dp))
                        .focusRequester(focusRequesters[i]),
                )
            }
        }
    }
}

@Preview
@Composable
fun PasswordTextFieldWithToggle(name: String = "PassTextField", isRequired: Boolean = false) {
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

@Preview
@Composable
fun CustomHugeTextField(name: String = "", isRequired: Boolean = false, placeholder: String = "") {
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