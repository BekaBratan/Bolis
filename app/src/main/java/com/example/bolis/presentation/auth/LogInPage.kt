package com.example.bolis.presentation.auth

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bolis.R
import com.example.bolis.data.api.appLanguages
import com.example.bolis.data.api.languageState
import com.example.bolis.data.api.systemLanguageChange
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomPasswordTextField
import com.example.bolis.ui.Elements.CustomTextField
import com.example.bolis.ui.Elements.Logo
import com.example.bolis.ui.Elements.OptionsButton
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily
import com.example.bolis.utils.SharedProvider
import java.util.Locale
import kotlin.math.log

@Preview
@Composable
fun LogInPage(
    createButtonClicked: () -> Unit = {},
    forgotButtonClicked: () -> Unit = {},
    nextButtonClicked: () -> Unit = {},
    viewModel: AuthViewModel = viewModel()
) {
    var languageIndex by remember { mutableIntStateOf(appLanguages.indexOf(languageState)) }
    val context = LocalContext.current
    val selectedLanguage = languageState
    val sharedProvider = SharedProvider(context)
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 52.dp),
        horizontalArrangement = Arrangement.End
    ) {
        OptionsButton(
            listOf("Қаз", "Рус", "Eng"),
            languageIndex,
            onClick = {
                languageIndex = it
                var langCode = when (languageIndex) {
                    0 -> "kk"
                    1 -> "ru"
                    2 -> "en"
                    else -> ""
                }
                languageState = appLanguages.find { it.code == langCode }!!
                systemLanguageChange(context, langCode)
            }
        )
    }

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
        CustomTextField(name = stringResource(R.string.email), text = phoneNumber, setText = { phoneNumber = it })
        Spacer(Modifier.size(24.dp))
        CustomPasswordTextField(name = stringResource(R.string.password), text = password, setText = { password = it })
        Spacer(Modifier.size(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.create_new_account),
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Blue50,
                modifier = Modifier.clickable { createButtonClicked() }
            )
            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Red50,
                modifier = Modifier.clickable { forgotButtonClicked() }
            )
        }

        Spacer(Modifier.size(42.dp))
        CustomButton(name = stringResource(R.string.login), onClick = {
            if (phoneNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(LogInRequest(phoneNumber.trim(), password.trim()))
            }
        })
    
        viewModel.logInResponse.observeForever {
            sharedProvider.saveUser(SignUpRequest(phoneNumber.trim(), password.trim()), "Bolis", "Helper")
            sharedProvider.setAuthorized()
            sharedProvider.saveToken(it!!.token)
            nextButtonClicked()
        }

        viewModel.errorMessageResponse.observeForever {
            Toast.makeText(context, it?.error?.split('"')[3], Toast.LENGTH_SHORT).show()
            Log.d("Error", it.toString())
        }
    }
}


