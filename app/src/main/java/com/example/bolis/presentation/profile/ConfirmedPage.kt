package com.example.bolis.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.data.ConfirmedState
import com.example.bolis.presentation.CustomBackButton
import com.example.bolis.presentation.CustomButton
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ConfirmedPage(
    pageState: ConfirmedState = ConfirmedState.PASSWORD,
    backButtonClicked:() -> Unit = {}
) {
    Box(Modifier.fillMaxSize()) {
        CustomBackButton(
            modifier = Modifier
                .padding(top = 28.dp, start = 24.dp),
            name = "Back"
        ) { backButtonClicked() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 166.dp, start = 34.dp, end = 34.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_check),
            contentScale = ContentScale.Fit,
            contentDescription = "check",
            modifier = Modifier
                .size(85.dp)
                .clickable(onClick = { })
        )

        Text(
            text =
                if (pageState == ConfirmedState.PASSWORD)
                    "Your password has been changed"
                else  if (pageState == ConfirmedState.DELETE)
                    "Your account has been deleted"
                else
                    "Your complaint has been successfully sent to technical support",
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(700),
            fontFamily = fontFamily,
            color = Black40
        )

        Text(
            text =
                if (pageState == ConfirmedState.PASSWORD)
                    "Now you can log in to my profile with a new password"
                else  if (pageState == ConfirmedState.DELETE)
                    "Your account has been archived, and you can restore your account within 3 months"
                else
                    "We are already looking into your problem",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(500),
            fontFamily = fontFamily,
            color = Black40
        )

        Spacer(Modifier.size(4.dp))

        CustomButton(
            name =
                if (pageState == ConfirmedState.PASSWORD || pageState == ConfirmedState.DELETE)
                    "Log In"
                else
                    "Back to profile",
            onClick = { }
            )
    }
}