package com.example.bolis.presentation.auth

import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomTextField
import com.example.bolis.ui.Elements.Logo
import com.example.bolis.ui.Elements.CustomPasswordTextField
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Red40
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun SignUpPage(
    backButtonClicked: () -> Unit = {},
    nextButtonClicked: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    CustomBackButton(
        modifier = Modifier
            .padding(top = 52.dp, start = 24.dp),
        name = "Log In"
    ) { backButtonClicked() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 34.dp, vertical = 112.dp),
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

        CustomTextField(name = "First name", isRequired = true, text = firstName, setText = { firstName = it })
        Spacer(Modifier.size(16.dp))

        CustomTextField(name = "Last name", text = lastName, setText = { lastName = it })
        Spacer(Modifier.size(16.dp))

        CustomTextField(name = "Email", isRequired = true, text = phoneNumber, setText = { phoneNumber = it })
        Spacer(Modifier.size(16.dp))

        CustomPasswordTextField(name = "Password", isRequired = true, text = password, setText = { password = it })
        
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Red40,
                modifier = Modifier.padding(vertical = 14.dp)
            )
        } else {
            Spacer(Modifier.size(32.dp))
        }

        CustomButton(
            name = "Register now",
            onClick = {
                if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
                    errorMessage = "Fill in all fields"
                } else {
                    errorMessage = ""
                    viewModel.signUp(SignUpRequest(phoneNumber.trim(), password.trim()))
                }
            }
        )

        viewModel.signUpResponse.observeForever {
            sharedProvider.saveUser(SignUpRequest(phoneNumber.trim(), password.trim()), firstName.trim(), lastName.trim())
            nextButtonClicked()
        }

        viewModel.errorMessageResponse.observeForever {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            errorMessage = it?.error ?: "Error with server"
        }
    }
}