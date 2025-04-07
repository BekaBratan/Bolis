package com.example.bolis.presentation.auth

import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
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
import androidx.core.os.LocaleListCompat
import com.example.bolis.R
import com.example.bolis.data.api.AppLocaleManager
import com.example.bolis.data.api.Language
import com.example.bolis.data.api.SettingState
import com.example.bolis.ui.Elements.CustomButton
import com.example.bolis.ui.Elements.CustomTextField
import com.example.bolis.ui.Elements.Logo
import com.example.bolis.ui.Elements.CustomPasswordTextField
import com.example.bolis.ui.Elements.OptionsButton
import com.example.bolis.ui.theme.Black20
import com.example.bolis.ui.theme.Blue50
import com.example.bolis.ui.theme.Red50
import com.example.bolis.ui.theme.fontFamily
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

@Preview
@Composable
fun LogInPage(
    createButtonClicked: () -> Unit = {},
    forgotButtonClicked: () -> Unit = {},
    nextButtonClicked: () -> Unit = {}
) {
    var languageIndex by remember { mutableIntStateOf(2) }

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
                val locale = when (languageIndex) {
                    0 -> Locale("kk")
                    1 -> Locale("ru")
                    2 -> Locale("en")
                    else -> Locale.getDefault()
                }
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
            text = stringResource(R.string.app_name),
            fontSize = 28.sp,
            fontWeight = FontWeight(600),
            fontFamily = fontFamily,
            color = Black20
        )
        Spacer(Modifier.size(80.dp))
        CustomTextField(name = "Phone number")
        Spacer(Modifier.size(24.dp))
        CustomPasswordTextField(name = "Password")
        Spacer(Modifier.size(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "CREATE NEW ACCOUNT",
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Blue50,
                modifier = Modifier.clickable { createButtonClicked() }
            )
            Text(
                text = "FORGOT YOUR PASSWORD ?",
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                fontFamily = fontFamily,
                color = Red50,
                modifier = Modifier.clickable { forgotButtonClicked() }
            )
        }

        Spacer(Modifier.size(42.dp))
        CustomButton(name = "Log In", onClick = { nextButtonClicked() })
    }
}


