package com.example.bolis.presentation.donate

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.bolis.ui.Elements.AddItemButton
import com.example.bolis.ui.Elements.AddItemDropdown
import com.example.bolis.ui.Elements.AddItemTextField
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.UploadImageButton
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily

@Preview
@Composable
fun AddItemPage(
    backButtonClicked:() -> Unit = {}
) {
    var nameError by remember { mutableStateOf(false) }
    var selectedButton by remember { mutableStateOf("") }
    var descriptionError by remember { mutableStateOf(false) }
    var listImages by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var uploadError by remember { mutableStateOf(false) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> listImages = uris }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            CustomBackButton(
                modifier = Modifier
                    .padding(top = 58.dp),
                name = "Back"
            ) { backButtonClicked() }
        }

        item {
            Text(
                text = "Fill in the information",
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Black50
            )
        }

        item {
            AddItemTextField(
                name = "Product name",
                isRequired = true,
                isError = nameError,
                placeholder = "Fill"
            )
        }

        item {
            AddItemDropdown(
                name = "Category",
                placeholder = "Choose"
            )
        }

        item {
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
        }

        item {
            AddItemTextField(
                name = "Description",
                isRequired = true,
                placeholder = "Tell about your product",
                isError = descriptionError,
                multiLine = true
            )
        }

        item {
            UploadImageButton(
                isError = uploadError,
                listImages = listImages,
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )
        }

        item {
            CustomButton(name = "Confirm")
        }
    }
}