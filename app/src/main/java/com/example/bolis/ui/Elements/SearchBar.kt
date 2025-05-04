package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey10
import com.example.bolis.ui.theme.Grey15
import com.example.bolis.ui.theme.Grey20
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun SearchBar(
    placeholder: String = "Search item here",
    searchText: String = "",
    setSearchText: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onClick: () -> Unit = {}
) {
    BasicTextField(
        value = searchText,
        onValueChange = { value -> setSearchText(value) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            fontFamily = fontFamily,
            color = Color.Black,
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (searchText.isEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = "Search",
                        tint = Grey30
                    )
                    Spacer(Modifier.size(5.dp))
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            fontSize = 12.sp,
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
            .fillMaxWidth()
            .height(32.dp)
            .clickable(onClick=onClick)
            .clip(RoundedCornerShape(10.dp))
            .background(Grey15),
    )
}