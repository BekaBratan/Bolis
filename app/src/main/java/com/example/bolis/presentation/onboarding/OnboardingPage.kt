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
            text = "Bôlis is a charity platform where you can share unwanted items or find something useful for yourself",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 17.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Image(
            painter = painterResource(id = R.drawable.onboard_2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
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
            text = "What Can You \n Do with Bôlis?",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "\uD83C\uDF81 Earn rewards — collect badges and achievements as you help others.\n" +
                    "\n" +
                    "\uD83D\uDEE1 Stay safe — enjoy secure, contactless handovers with verified users.\n" +
                    "\n" +
                    "\uD83C\uDF10 Join the movement — be part of a growing network making a positive impact.",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 17.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Image(
            painter = painterResource(id = R.drawable.onboard_1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
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
            text = "Why Choose Bôlis?",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "\uD83D\uDC9B Simplicity — minimal steps, user-friendly interface.\n" +
                    "\uD83C\uDF0D Community — a place where everyone helps and inspires.\n" +
                    "\uD83C\uDF31 Eco-friendly — reduce waste, reuse, and give items a second life.",
            fontFamily = fontFamily,
            color = White50,
            fontSize = 17.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(46.dp))
        Image(
            painter = painterResource(id = R.drawable.onboard_3),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )
    }
}