package com.example.bolis.presentation.donate

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.api.languageState
import com.example.bolis.data.models.Category
import com.example.bolis.data.models.GiveProductRequest
import com.example.bolis.ui.Elements.AddItemButton
import com.example.bolis.ui.Elements.AddItemDropdown
import com.example.bolis.ui.Elements.AddItemTextField
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.UploadImageButton
import com.example.bolis.ui.theme.Black40
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@Preview
@Composable
fun AddItemPage(
    backButtonClicked:() -> Unit = {},
    onConfirmButtonClick:() -> Unit = {},
    viewModel: DonateViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)

    var nameError by remember { mutableStateOf(false) }
    var selectedButton by remember { mutableStateOf("New") }
    var descriptionError by remember { mutableStateOf(false) }
    var listImages by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var uploadError by remember { mutableStateOf(false) }
    var options by remember { mutableStateOf(listOf<Category>()) }
    var selectedOption by remember { mutableStateOf(0) }

    val (productName, setProductName) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> listImages = uris }
    )

    LaunchedEffect(Unit) {
        viewModel.getCategories(sharedProvider.getToken())
    }

    viewModel.categoriesResponse.observeForever {
        if (it != null) {
            options = it.categories
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, bottom = 30.dp),
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
                placeholder = "Fill",
                text = productName,
                setText = setProductName
            )
        }

        item {
            AddItemDropdown(
                name = "Category",
                placeholder = "Choose",
                options = options.map { it.name },
                optionSelected = { selectedOption = it }
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
                multiLine = true,
                text = description,
                setText = setDescription
            )
        }

        item {
            UploadImageButton(
                isError = uploadError,
                listImages = listImages,
                onUploadImage = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )
        }

        item {
            CustomButton(name = "Confirm", onClick = {
                if (listImages.isNotEmpty()) {
                    val imageParts = listImages.map { uri ->
                        convertUriToMultipart(uri, context)
                    }

                    // Send the request to the ViewModel
                    viewModel.giveProduct(
                        sharedProvider.getToken(),
                        GiveProductRequest(
                            name = productName,
                            description = description,
                            condition = selectedButton,
                            categoryId = options[selectedOption].iD,
                            images = imageParts
                        )
                    )
                } else {
                    // Handle error if no images selected
                    uploadError = true
                }
            })

            viewModel.giveProductResponse.observeForever {
                if (it != null) {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    onConfirmButtonClick()
                }
            }

            viewModel.errorResponse.observeForever {
                if (it != null) {
                    when (it) {
                        "Name is required" -> nameError = true
                        "Description is required" -> descriptionError = true
                        "Images are required" -> uploadError = true
                        else -> Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

fun convertUriToMultipart(uri: Uri, context: Context): MultipartBody.Part {
    val file = File(context.cacheDir, uri.lastPathSegment ?: "image.jpg")
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = file.outputStream()

    inputStream?.copyTo(outputStream)
    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("images", file.name, requestFile)
}