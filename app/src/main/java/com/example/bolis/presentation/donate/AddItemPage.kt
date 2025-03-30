package com.example.bolis.presentation.donate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.presentation.AddItemButton
import com.example.bolis.presentation.AddItemDropdown
import com.example.bolis.presentation.AddItemTextField
import com.example.bolis.presentation.CustomBackButton
import com.example.bolis.presentation.CustomButton
import com.example.bolis.presentation.UploadImageButton
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun AddItemPage(
    backButtonClicked:() -> Unit = {}
) {
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
            .padding(top = 74.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Fill in the information",
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50
        )

        var nameError by remember { mutableStateOf(false) }
        AddItemTextField(name = "Product name", isRequired = true, isError = nameError, placeholder = "Fill")

        AddItemDropdown(name = "Category", placeholder = "Choose")

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "Status",
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Black40
            )
            var selectedButton by remember { mutableStateOf("") }
            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                AddItemButton(
                    name = "New",
                    isSelected = selectedButton == "New",
                    onClick = { selectedButton = "New" })
                AddItemButton(
                    name = "Used",
                    isSelected = selectedButton == "Used",
                    onClick = { selectedButton = "Used" })
            }
        }

        var descriptionError by remember { mutableStateOf(false) }
        AddItemTextField(name = "Description", isRequired = true, placeholder = "Tell about your product", isError = descriptionError, multiLine = true)

        var uploadError by remember { mutableStateOf(false) }
        UploadImageButton(isError = uploadError, onClick = {})

        CustomButton(name = "Confirm")
    }
}