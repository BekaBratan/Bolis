package com.example.bolis.presentation.donate

import android.content.Context
import android.net.Uri
import android.util.Log
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
import androidx.compose.runtime.collectAsState
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
import com.example.bolis.data.api.navBarStateChange
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
    backButtonClicked: () -> Unit = {},
    onConfirmButtonClick: () -> Unit = {},
    viewModel: DonateViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedProvider = remember { SharedProvider(context) }
    navBarStateChange(false)

    var nameError by remember { mutableStateOf(false) }
    var selectedButton by remember { mutableStateOf("New") }
    var descriptionError by remember { mutableStateOf(false) }
    var listImages by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var uploadError by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(0) }

    val (productName, setProductName) = remember { mutableStateOf("") }
    val (description, setDescription) = remember { mutableStateOf("") }

    val categories by viewModel.categoriesResponse.collectAsState()
    val giveProductResponse by viewModel.giveProductResponse.collectAsState()
    val errorResponse by viewModel.errorResponse.collectAsState()

    val options = categories?.categories ?: emptyList()

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris -> listImages = uris }

    LaunchedEffect(Unit) {
        viewModel.getCategories(sharedProvider.getToken())
    }

    LaunchedEffect(giveProductResponse) {
        giveProductResponse?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            onConfirmButtonClick()
        }
    }

    LaunchedEffect(errorResponse) {
        errorResponse?.let {
            when (it) {
                "Name is required" -> nameError = true
                "Description is required" -> descriptionError = true
                "Images are required" -> uploadError = true
                else -> Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            CustomBackButton(modifier = Modifier.padding(top = 28.dp), name = "Back") {
                backButtonClicked()
            }
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
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "Status",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = fontFamily,
                    color = Black40
                )
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    AddItemButton("New", selectedButton == "New") { selectedButton = "New" }
                    AddItemButton("Used", selectedButton == "Used") { selectedButton = "Used" }
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
            CustomButton("Confirm") {
                nameError = productName.isBlank()
                descriptionError = description.isBlank()
                uploadError = listImages.isEmpty()

                if (!nameError && !descriptionError && !uploadError) {
                    val categoryId = categories?.categories?.get(selectedOption)?.iD ?: 75
                    val condition = if (selectedButton == "New") "new" else "used"
                    val giveProductsBody = GiveProductRequest(
                        categoryId = categoryId,
                        description = description,
                        condition = condition,
                        name = productName
                    )

                    viewModel.giveProduct(
                        token = sharedProvider.getToken(),
                        giveProductsBody = giveProductsBody,
                        images = listImages.map { convertUriToFile(it, context) }
                    )
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

fun convertUriToFile(uri: Uri, context: Context): File {
    val file = File(context.cacheDir, uri.lastPathSegment ?: "image.jpg")
    file.createNewFile()
    file.outputStream().use {
        context.contentResolver.openInputStream(uri)?.copyTo(it)
    }
    Log.d("convertUriToFile", "File path: ${file.absolutePath}")
    return file
}