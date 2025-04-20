package com.example.bolis.ui.Elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CustomCodeTextField(
    text: String = "",
    setText: (String) -> Unit = {}
) {

    Box {
        val focusRequesters = List(6) { FocusRequester() }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until 6) {
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
                                if (i < 5) {
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