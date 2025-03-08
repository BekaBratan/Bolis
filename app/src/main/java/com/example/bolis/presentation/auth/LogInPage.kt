package com.example.bolis.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.presentation.CustomButton
import com.example.bolis.presentation.CustomTextField
import com.example.bolis.presentation.Logo
import com.example.bolis.presentation.PasswordTextFieldWithToggle
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily

@Composable
fun LogInPage(createButtonClicked:() -> Unit, forgotButtonClicked:() -> Unit, nextButtonClicked:() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 34.dp, vertical = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        Spacer(Modifier.size(12.dp))
        Text(
            text = "Bolis",
            fontSize = 28.sp,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = Black20
        )
        Spacer(Modifier.size(80.dp))
        CustomTextField(name = "Phone number")
        Spacer(Modifier.size(24.dp))
        PasswordTextFieldWithToggle(name = "Password")
        Spacer(Modifier.size(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "CREATE NEW ACCOUNT",
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Blue50,
                modifier = Modifier.clickable { createButtonClicked() }
            )
            Text(
                text = "FORGOT YOUR PASSWORD ?",
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Red50,
                modifier = Modifier.clickable { forgotButtonClicked() }
            )
        }

        Spacer(Modifier.size(42.dp))
        CustomButton(name = "Log In", onClick = { nextButtonClicked() })
    }
}