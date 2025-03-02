package com.example.bolis.presentation

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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bolis.LogInScreen
import com.example.bolis.RegCodeScreen
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.fontFamily

@Preview(device = Devices.DEFAULT, widthDp = 400, heightDp = 800, backgroundColor = 0xFFFFFF)
@Composable
fun SignUpPage(navController: NavHostController = rememberNavController()) {
    Box(Modifier.fillMaxSize()) {
        CustomBackButton(
            modifier = Modifier
                .padding(top = 28.dp, start = 24.dp),
            name = "Log In", { navController.navigate(LogInScreen) }
        )

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

            CustomTextField(name = "First name", true)
            Spacer(Modifier.size(16.dp))

            CustomTextField(name = "Last name")
            Spacer(Modifier.size(16.dp))

            CustomTextField(name = "Phone number", true)
            Spacer(Modifier.size(16.dp))

            PasswordTextFieldWithToggle(name = "Password", true)
            Spacer(Modifier.size(32.dp))

            CustomButton(name = "Register now", { navController.navigate(RegCodeScreen) })
        }
    }
}