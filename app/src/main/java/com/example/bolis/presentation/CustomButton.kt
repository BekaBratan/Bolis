package com.example.bolis.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey10
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Grey50
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.White40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun CustomButton(name: String = "Button", onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(Green50)
            .clickable(onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun HugeCustomButton(name: String = "Button", onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Green50)
            .clickable(onClick = { onClick() }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight(900),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun CustomBackButton(modifier: Modifier = Modifier, name: String = "Back", onClick: () -> Unit = {}) {
    Row (
        modifier = modifier
            .height(24.dp)
            .clickable(onClick = { onClick() }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            tint = Dark50
        )
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            fontFamily = fontFamily,
            color = Dark50
        )
    }
}

@Preview
@Composable
fun Logo () {
    Image(
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = "show",
        Modifier.height(100.dp)
    )
}

@Preview
@Composable
fun ProfileButton(name: String = "Button", onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(67.dp)
            .border(2.dp, Green50, RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp))
            .background(Grey10)
            .clickable(onClick = { onClick() })
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profile_stroke),
            contentScale = ContentScale.Fit,
            contentDescription = "show",
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = {
                })
        )
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Grey50
            )
            Text(
                text = "Show profile",
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Grey50
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentScale = ContentScale.Fit,
            contentDescription = "show",
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .clickable(onClick = {
                })
        )
    }
}

@Preview
@Composable
fun SettingsButton(name: String = "Button", onClick: () -> Unit = {}, icon: Int = R.drawable.ic_lock) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .border(2.dp, Green50, RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp))
            .background(Grey10)
            .clickable(onClick = { onClick() })
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(icon),
            contentScale = ContentScale.Fit,
            contentDescription = "show",
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = {
                })
        )
        Box(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Grey50
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentScale = ContentScale.Fit,
            contentDescription = "show",
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .clickable(onClick = {
                })
        )
    }
}

@Preview
@Composable
fun ProfileImageCard(onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .border(2.dp, Green50, RoundedCornerShape(5.dp))
            .clip(shape = RoundedCornerShape(5.dp))
            .clickable(onClick = { onClick() })
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profile_img),
            contentScale = ContentScale.Fit,
            contentDescription = "show",
            modifier = Modifier
                .size(50.dp)
                .clickable(onClick = { })
        )
        Text(
            text = "Change Profile Image",
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Blue50
        )
    }
}

@Preview
@Composable
fun AddItemButton(name: String = "Button", isSelected: Boolean = false, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .width(84.dp)
            .height(42.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(White40)
            .border(
                width = if (isSelected) 1.dp else 0.dp,
                color = if (isSelected) Green50 else Color.Transparent,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable(onClick = {
                onClick()
            }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = if (isSelected) Green50 else Grey30
        )
    }
}

@Preview
@Composable
fun UploadImageButton(isError: Boolean = false, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "Upload image of product*",
            fontSize = 15.sp,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = Black40
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(White40)
                .border(
                    width = if (isError) 1.dp else 0.dp,
                    color = if (isError) Red50 else Color.Transparent,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 18.dp),
                text = "JPEG,PNG weighing no more than 10MB",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Grey30
            )
            val image = R.drawable.ic_image
            Image(
                painter = painterResource(image),
                contentScale = ContentScale.Fit,
                contentDescription = "image",
                modifier = Modifier
                    .size(50.dp),
                colorFilter = ColorFilter.tint(Grey30)
            )
            CustomButton(name = "Upload image", onClick = { onClick() })
        }
    }
}