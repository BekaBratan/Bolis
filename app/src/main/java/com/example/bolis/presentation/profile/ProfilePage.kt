package com.example.bolis.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.presentation.ProfileButton
import com.example.bolis.presentation.SettingsButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Dark50
import com.example.bolis.ui.theme.Green20
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey40
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun ProfilePage(
    profileButtonClicked: () -> Unit = {},
    changePassButtonClicked: () -> Unit = {},
    supportButtonClicked: () -> Unit = {},
    deleteButtonClicked: () -> Unit = {},
) {
    var isLogout by remember { mutableStateOf(false) }

    Column(
        modifier =
            if (isLogout)
                Modifier
                    .fillMaxSize()
                    .blur(16.dp)
                    .padding(top = 100.dp, start = 24.dp, end = 24.dp)
            else
                Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
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
        ProfileButton(name = "Bekarys", onClick = profileButtonClicked)
        Spacer(Modifier.size(24.dp))
        Text(
            text = "Settings",
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Grey40
        )
        SettingsButton(name = "Change Password", onClick = changePassButtonClicked, icon = R.drawable.ic_lock)
        SettingsButton(name = "Help and Support", onClick = supportButtonClicked, icon = R.drawable.ic_headphone)
        SettingsButton(name = "Delete Account", onClick = deleteButtonClicked, icon = R.drawable.ic_trash)
        SettingsButton(name = "Logout", onClick = { isLogout = true }, icon = R.drawable.ic_logout)
    }


    if (isLogout) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 52.dp)
                    .fillMaxWidth()
                    .background(Green20)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cross),
                    contentDescription = "back",
                    tint = Dark50,
                    modifier = Modifier
                        .padding(top = 12.dp, end = 16.dp)
                        .align(Alignment.TopEnd)
                        .clickable(onClick = { isLogout = false })
                )

                Column(
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Log out",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(700),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily,
                        color = Grey40
                    )
                    Text(
                        text = "Do you want to log out?",
                        fontSize = 13.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily,
                        color = Grey40
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "No",
                            fontSize = 13.sp,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Center,
                            fontFamily = fontFamily,
                            color = Green50,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .width(40.dp)
                                .clickable(onClick = {})
                        )
                        Text(
                            text = "Yes, log out",
                            fontSize = 13.sp,
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Center,
                            fontFamily = fontFamily,
                            color = Color.White,
                            modifier = Modifier
                                .background(Green50)
                                .clickable(onClick = { })
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }

}