package com.example.bolis.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.data.CodeState
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.VerificationRequest
import com.example.bolis.presentation.auth.AuthViewModel
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.Elements.CustomCodeTextField
import com.example.bolis.ui.Elements.CustomHugeButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider

@Preview
@Composable
fun ConfirmationCodePage(
    codeState: CodeState = CodeState.PASSWORD,
    backButtonClicked: () -> Unit = {},
    nextButtonClicked: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val codeState = codeState
    val (text, setText) = remember { mutableStateOf("") }
    val sharedProvider = SharedProvider(context)
    val flow = if (codeState == CodeState.PASSWORD) "reset" else "signup"

    CustomBackButton(
        modifier = Modifier
            .padding(top = 52.dp, start = 24.dp),
        name = "Back"
    ) { backButtonClicked() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 34.dp, vertical = 211.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter the 4-digit code",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = Black50
        )
        Spacer(Modifier.size(20.dp))


        CustomCodeTextField(
            text = text,
            setText = setText
        )
        Spacer(Modifier.size(20.dp))

//        Text(
//            text = "Send the code again at 0:30",
//            fontSize = 16.sp,
//            textAlign = TextAlign.Center,
//            fontWeight = FontWeight(400),
//            fontFamily = fontFamily,
//            color = Black50
//        )
        Spacer(Modifier.size(35.dp))

        CustomHugeButton(
            name = if (codeState == CodeState.PASSWORD) "Recover password" else "Log In"
        ) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            viewModel.verify(VerificationRequest(code = text, email = sharedProvider.getEmail(), flow = flow))
        }

        viewModel.verifyResponse.observeForever {
            sharedProvider.setAuthorized()
            nextButtonClicked()
        }

        viewModel.errorResponse.observeForever {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}