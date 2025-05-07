package com.example.bolis.ui.Elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey15
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun SearchBar(
    placeholder: String = "Search item here",
    searchText: String = "",
    setSearchText: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    isFavorite: Boolean = true,
    isBack: Boolean = true,
    isChat: Boolean = true,
    isSearch: Boolean = false
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        if (isFavorite) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "favorite",
                tint = Green50,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onFavoriteClick)
            )
            Spacer(modifier = Modifier.size(10.dp))
        } else if (isBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                tint = Green50,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onBackClick)
            )
            Spacer(modifier = Modifier.size(10.dp))
        }

        Box (
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = { onSearchClick() }),
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = { value -> setSearchText(value) },
                singleLine = true,
                enabled = isSearch,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(
                    fontSize = 16.sp,
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
                                tint = Grey30,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            Spacer(Modifier.size(5.dp))
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
                        innerTextField()
                    }
                },
                modifier = Modifier
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Grey15),
            )
        }

        if (isChat) {
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_chat),
                contentDescription = "chat",
                tint = Green50,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onChatClick)
            )
        } else if (isSearch) {
            Spacer(modifier = Modifier.size(10.dp))
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Green50)
                    .padding(horizontal = 10.dp)
                    .clickable(onClick = { onSearch(searchText) }),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Search",
                    textAlign = TextAlign.Center,
                    color = White50,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = fontFamily
                )
            }
        }
    }

}