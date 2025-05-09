package com.example.bolis.presentation.profile

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.data.models.ProfileResponse
import com.example.bolis.presentation.auth.AuthViewModel
import com.example.bolis.presentation.donate.convertUriToFile
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomDropdown
import com.example.bolis.ui.Elements.CustomTextField
import com.example.bolis.ui.Elements.ProfileImageCard
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.Constants.Companion.IMAGE_URL
import com.example.bolis.utils.SharedProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import androidx.core.net.toUri

@Preview
@Composable
fun ProfileEditPage(
    backButtonClicked:() -> Unit = {},
    viewModel: ProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    navBarStateChange(false)

    var profileBody by remember { mutableStateOf(ProfileResponse(
        avatarUrl = "",
        email = "",
        firstName = "",
        id = "",
        lastName = "",
        role = "",
        username = ""
    )) }
    var firstName by remember { mutableStateOf("") }
    var imageURL by remember { mutableStateOf("https://plus.unsplash.com/premium_photo-1674902194669-9aadae2f76a1?q=80&w=1930&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D") }
    var uploadedImage by remember { mutableStateOf<Uri?>(null) }
    var lastName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf(sharedProvider.getCity()) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> uploadedImage = uri }

//    Toast.makeText(context, sharedProvider.getToken(), Toast.LENGTH_SHORT).show()
    LaunchedEffect(Unit)  {
        viewModel.getProfile(sharedProvider.getToken())
        viewModel.getDeliveryAddress(sharedProvider.getToken())
    }

    viewModel.profileResponse.observeForever { response ->
        if (response != null) {
            profileBody = response
            firstName = response.firstName
            lastName = response.lastName
            val timestamp = System.currentTimeMillis()
            imageURL = "${IMAGE_URL}${response.avatarUrl}?t=$timestamp"
        }
        Log.d("Profile", response.toString())
    }

    viewModel.deliveryAddressResponse.observeForever { response ->
        if (response != null) {
            city = response.addressLine
        }
        Log.d("Profile", response.toString())
    }

    viewModel.errorMessageResponse.observeForever { response ->
        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show()
    }

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
            .padding(top = 84.dp, start = 34.dp, end = 34.dp),
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
        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ProfileImageCard(
                url = uploadedImage?.toString() ?: imageURL,
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )
        }
        Spacer(Modifier.size(4.dp))

        CustomTextField(name = "Name", text = firstName, setText = { firstName = it })

        CustomTextField(name = "Surname", text = lastName, setText = { lastName = it })

        CustomDropdown(name = "City", text = city, setText = { city = it })

        Spacer(Modifier.size(4.dp))

        CustomButton(name = "Save", onClick = {
            profileBody.firstName = firstName
            profileBody.lastName = lastName
            viewModel.updateProfile(sharedProvider.getToken(), profileBody,
                if (uploadedImage!=null)
                    convertUriToFile(uploadedImage!!, context)
                else
                    null
            )
            sharedProvider.setCity(city)
        })

        viewModel.profileUpdateResponse.observeForever {
            backButtonClicked()
        }
    }
}

