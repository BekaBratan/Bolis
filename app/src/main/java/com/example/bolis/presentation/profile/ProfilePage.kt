package com.example.bolis.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.presentation.ProfileButton
import com.example.bolis.presentation.SettingsButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Grey40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ProfilePage() {
    Column(
        modifier = Modifier
            .padding(top = 100.dp, start = 24.dp, end = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Welcome Bekarys !",
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50
        )
        Spacer(Modifier.size(22.dp))
        ProfileButton(name = "Bekarys", onClick = {})
        Spacer(Modifier.size(24.dp))
        Text(
            text = "Settings",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Grey40
        )
        SettingsButton(name = "Change Password", onClick = {}, icon = R.drawable.ic_lock)
        SettingsButton(name = "Help and Support", onClick = {}, icon = R.drawable.ic_headphone)
        SettingsButton(name = "Delete Account", onClick = {}, icon = R.drawable.ic_trash)
        SettingsButton(name = "Logout", onClick = {}, icon = R.drawable.ic_logout)
    }
}