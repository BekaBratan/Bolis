package com.example.bolis.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.example.bolis.data.PasswordRecoveryState
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomTextField
import com.example.bolis.ui.Elements.CustomPasswordTextField
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun PasswordRecoveryPage(
    isSetNewPassword: PasswordRecoveryState = PasswordRecoveryState.PHONE_NUMBER,
    backButtonClicked:() -> Unit = {},
    nextButtonClicked:() -> Unit = {}
) {
    val isSetNewPassword = isSetNewPassword

    CustomBackButton(
        modifier = Modifier
            .padding(top = 52.dp, start = 24.dp),
        name = "Back"
    ) { backButtonClicked() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 160.dp, start = 34.dp, end = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isSetNewPassword == PasswordRecoveryState.SUCCESS){
            Spacer(Modifier.size(64.dp))
            Image(
                painter = painterResource(R.drawable.ic_check),
                contentScale = ContentScale.Fit,
                contentDescription = "check",
                modifier = Modifier
                    .size(85.dp)
                    .clickable(onClick = { })
            )
            Spacer(Modifier.size(16.dp))
        }


        Text(
            text = if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) "Forgot Your Password ?" else  if (isSetNewPassword == PasswordRecoveryState.NEW_PASSWORD) "Set a new password" else "Your password has\n" +
                    "been changed",
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) Red50 else Black40
        )
        Spacer(Modifier.size(24.dp))

        if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) {
            Text(
                text = "Please enter the phone number you use to log in. We’ll send you a code to set  a new password.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = Black50,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(Modifier.size(38.dp))
            CustomTextField(name = "Phone number")
            Spacer(Modifier.size(32.dp))
        } else if (isSetNewPassword == PasswordRecoveryState.NEW_PASSWORD) {
            CustomPasswordTextField(name = "Enter a new password")
            Spacer(Modifier.size(32.dp))
        }

        CustomButton(
            name = if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) "Confirm" else if (isSetNewPassword == PasswordRecoveryState.NEW_PASSWORD) "Submit" else "Log In",
            onClick = { nextButtonClicked() }
        )
    }
}