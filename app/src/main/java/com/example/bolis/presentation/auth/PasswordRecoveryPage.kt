package com.example.bolis.presentation.auth

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.data.PasswordRecoveryState
import com.example.bolis.presentation.CustomBackButton
import com.example.bolis.presentation.CustomButton
import com.example.bolis.presentation.CustomTextField
import com.example.bolis.presentation.PasswordTextFieldWithToggle
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily

@Composable
fun PasswordRecoveryPage(isSetNewPassword: PasswordRecoveryState = PasswordRecoveryState.PHONE_NUMBER, backButtonClicked:() -> Unit, nextButtonClicked:() -> Unit) {
    val isSetNewPassword = isSetNewPassword

    Box(Modifier.fillMaxSize()) {
        CustomBackButton(
            modifier = Modifier
                .padding(top = 28.dp, start = 24.dp),
            name = "Back"
        ) { backButtonClicked() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp, vertical = 158.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) "Forgot Your Password ?" else  if (isSetNewPassword == PasswordRecoveryState.NEW_PASSWORD) "Set a new password" else "Your password has\n" +
                        "been changed",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) Red50 else Black20
            )
            Spacer(Modifier.size(24.dp))

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

            if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) {
                CustomTextField(name = "Phone number")
                Spacer(Modifier.size(32.dp))
            } else if (isSetNewPassword == PasswordRecoveryState.NEW_PASSWORD) {
                PasswordTextFieldWithToggle(name = "Enter a new password")
                Spacer(Modifier.size(32.dp))
            }

            CustomButton(
                name = if (isSetNewPassword == PasswordRecoveryState.PHONE_NUMBER) "Confirm" else if (isSetNewPassword == PasswordRecoveryState.NEW_PASSWORD) "Submit" else "Log In"
            ) { nextButtonClicked() }
        }
    }
}