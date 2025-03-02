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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bolis.HomeScreen
import com.example.bolis.NewPassScreen
import com.example.bolis.RecPassScreen
import com.example.bolis.SignUpScreen
import com.example.bolis.data.CodeState
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily

@Preview(device = Devices.DEFAULT, widthDp = 400, heightDp = 800, backgroundColor = 0xFFFFFF)
@Composable
fun ConfirmationCodePage(codeState: CodeState = CodeState.PASSWORD, navController: NavHostController = rememberNavController()) {
    val codeState = codeState

    Box(Modifier.fillMaxSize()) {
        CustomBackButton(
            modifier = Modifier
                .padding(top = 28.dp, start = 24.dp),
            name = "Back", { if (codeState == CodeState.REGISTRATION) { navController.navigate(SignUpScreen) } else { navController.navigate(RecPassScreen) } }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp, vertical = 211.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter the 4-digit code",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Black50
            )
            Spacer(Modifier.size(20.dp))

            CustomCodeTextField()
            Spacer(Modifier.size(20.dp))

            Text(
                text = "Send the code again at 0:30",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(400),
                fontFamily = fontFamily,
                color = Black50
            )
            Spacer(Modifier.size(35.dp))

            HugeCustomButton(name = if (codeState == CodeState.PASSWORD) "Recover password" else "Log In"
            ) {
                if (codeState == CodeState.REGISTRATION) {
                    navController.navigate(HomeScreen)
                } else {
                    navController.navigate(NewPassScreen)
                }
            }
        }
    }
}