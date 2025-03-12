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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey10
import com.example.bolis.ui.theme.Grey50
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