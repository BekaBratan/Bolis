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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomTextField
import com.example.bolis.ui.Elements.Logo
import com.example.bolis.ui.Elements.CustomPasswordTextField
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun SignUpPage(
    backButtonClicked: () -> Unit = {},
    nextButtonClicked: () -> Unit = {}
) {
    CustomBackButton(
        modifier = Modifier
            .padding(top = 52.dp, start = 24.dp),
        name = "Log In"
    ) { backButtonClicked() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 34.dp, vertical = 112.dp),
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

        CustomTextField(name = "First name", true)
        Spacer(Modifier.size(16.dp))

        CustomTextField(name = "Last name")
        Spacer(Modifier.size(16.dp))

        CustomTextField(name = "Phone number", true)
        Spacer(Modifier.size(16.dp))

        CustomPasswordTextField(name = "Password", true)
        Spacer(Modifier.size(32.dp))

        CustomButton(
            name = "Register now",
            onClick = { nextButtonClicked() }
        )
    }
}