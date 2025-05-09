package com.example.bolis.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomHugeTextField
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun SupportPage(
    backButtonClicked:() -> Unit = {},
    confirmButtonClicked:() -> Unit = {}
) {
    navBarStateChange(false)

    CustomBackButton(
        modifier = Modifier
            .padding(top = 28.dp, start = 24.dp),
        name = "Back"
    ) { backButtonClicked() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp, start = 34.dp, end = 34.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Help and support",
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50
        )
        Spacer(Modifier.size(0.dp))

        Text(
            text = "Please write your problem below",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(500),
            fontFamily = fontFamily,
            color = Black40,
            modifier = Modifier.fillMaxWidth()
        )

        CustomHugeTextField(placeholder = "How can we help you?")
        Spacer(Modifier.size(4.dp))

        CustomButton(name = "Send", onClick = confirmButtonClicked)
    }
}