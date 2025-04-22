package com.example.bolis.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.ui.theme.White50
import com.example.bolis.ui.theme.fontFamily

@Preview(showBackground = true, backgroundColor = 0xFF278867)
@Composable
fun OnboardingPage1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the \n\" Bolis \" application !",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Our project supports the well-being of the population and helps families in need.",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 17.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF278867)
@Composable
fun OnboardingPage2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the \n\" Bolis \" application !",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Our project supports the well-being of the population and helps families in need.",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 17.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF278867)
@Composable
fun OnboardingPage3() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the \n\" Bolis \" application !",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Our project supports the well-being of the population and helps families in need.",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 17.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )
    }
}