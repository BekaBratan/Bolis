package com.example.bolis.ui.Elements

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.fontFamily

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