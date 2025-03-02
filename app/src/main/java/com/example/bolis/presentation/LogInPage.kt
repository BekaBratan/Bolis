package com.example.bolis.presentation

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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bolis.HomeScreen
import com.example.bolis.RecPassScreen
import com.example.bolis.SignUpScreen
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily

@Preview(device = Devices.DEFAULT, widthDp = 400, heightDp = 800, backgroundColor = 0xFFFFFF)
@Composable
fun LogInPage(navController: NavHostController = rememberNavController()) {
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
                modifier = Modifier.clickable { navController.navigate(SignUpScreen) }
            )
            Text(
                text = "FORGOT YOUR PASSWORD ?",
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Red50,
                modifier = Modifier.clickable { navController.navigate(RecPassScreen) }
            )
        }

        Spacer(Modifier.size(42.dp))
        CustomButton(name = "Log In", onClick = { navController.navigate(HomeScreen) })
    }
}