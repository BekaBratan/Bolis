package com.example.bolis.presentation.profile

import android.widget.Toast
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.api.navBarStateChange
import com.example.bolis.data.models.UpdatePasswordRequest
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomPasswordTextField
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun ChangePasswordPage(
    backButtonClicked:() -> Unit = {},
    confirmButtonClicked:() -> Unit = {},
    viewModel: ProfileViewModel = viewModel()
) {
    navBarStateChange(false)
    val context = LocalContext.current
    val sharedProvider = SharedProvider(context)

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
            text = "Change Password",
            fontSize = 22.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            color = Black50
        )
        Spacer(Modifier.size(4.dp))
        CustomPasswordTextField(name = "New password", text = newPassword, setText = { newPassword = it })
        CustomPasswordTextField(name = "Confirm new password", text = confirmPassword, setText = { confirmPassword = it })
        Spacer(Modifier.size(4.dp))
        CustomButton(name = "Change", onClick = {
            if (newPassword == confirmPassword) {
                viewModel.updatePassword(
                    sharedProvider.getToken(),
                    UpdatePasswordRequest(
                        confirmPassword = confirmPassword,
                        email = sharedProvider.getEmail(),
                        newPassword = newPassword
                    )
                )
            } else {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        })
        
        viewModel.messageResponse.observeForever {
//            Toast.makeText(context, "Passwords updated successfully", Toast.LENGTH_SHORT).show()
            confirmButtonClicked()
        }

        viewModel.errorMessageResponse.observeForever {
            Toast.makeText(context, it?.error?.split('"')[3], Toast.LENGTH_SHORT).show()
        }
    }
}