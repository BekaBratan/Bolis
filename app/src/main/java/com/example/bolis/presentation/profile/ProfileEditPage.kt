package com.example.bolis.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomDropdown
import com.example.bolis.ui.Elements.CustomTextField
import com.example.bolis.ui.Elements.ProfileImageCard
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun ProfileEditPage(
    backButtonClicked:() -> Unit = {}
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    var firstName by remember { mutableStateOf(sharedProvider.getFirstName()) }
    var lastName by remember { mutableStateOf(sharedProvider.getLastName()) }
    var city by remember { mutableStateOf(sharedProvider.getCity()) }

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
            .padding(top = 140.dp, start = 34.dp, end = 34.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Welcome ${sharedProvider.getFirstName()} !",
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50
        )
        Spacer(Modifier.size(4.dp))
        ProfileImageCard()
        Spacer(Modifier.size(4.dp))
        CustomTextField(name = "Name", text = firstName, setText = { firstName = it })
        CustomTextField(name = "Surname", text = lastName, setText = { lastName = it })
        CustomDropdown(name = "City", text = city, setText = { city = it })
        Spacer(Modifier.size(4.dp))
        CustomButton(name = "Save", onClick = {
            sharedProvider.setFirstName(firstName)
            sharedProvider.setLastName(lastName)
            sharedProvider.setCity(city)
            backButtonClicked()
        })
    }
}