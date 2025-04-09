package com.example.bolis.presentation.profile

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bolis.R
import com.example.bolis.data.api.Language
import com.example.bolis.data.api.appLanguages
import com.example.bolis.data.api.languageState
import com.example.bolis.data.api.systemLanguageChange
import com.example.bolis.ui.Elements.CustomBackButton
import com.example.bolis.ui.theme.Black50
import com.example.bolis.ui.theme.Green50
import com.example.bolis.ui.theme.Grey10
import com.example.bolis.ui.theme.Grey30
import com.example.bolis.ui.theme.Grey40
import com.example.bolis.ui.theme.fontFamily
import java.util.Locale


@Preview
@Composable
fun ChangeLanguagePage(
    backButtonClicked:() -> Unit = {},
) {
    val context = LocalContext.current
    val selectedLanguage = languageState

    CustomBackButton(
        modifier = Modifier
            .padding(top = 28.dp, start = 24.dp),
        name = "Back"
    ) { backButtonClicked() }

    Text(
        text = stringResource(R.string.name),
        fontSize = 28.sp,
        fontWeight = FontWeight(600),
        fontFamily = fontFamily,
        color = Black50,
    )

    LazyColumn(
        modifier = Modifier
            .padding(top = 100.dp, start = 24.dp, end = 24.dp)
            .border(1.dp, Green50, RoundedCornerShape(7.dp))
            .clip(RoundedCornerShape(7.dp))
            .background(Grey10)
    ) {
        items(appLanguages) { language ->
            LanguageItem(
                language = language,
                onClick = {
                    systemLanguageChange(context, language.code)
                }
            )
            if (language != appLanguages.last()) {
                Spacer(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Grey30)
                )
            }
        }
    }
}

@Preview
@Composable
fun LanguageItem(
    language: Language = Language("en", "English"),
    onClick: () -> Unit = {}
) {
    Row(
        Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = { onClick() }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = language.displayLanguage,
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Black50
            )
            Text(
                text = language.code,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                fontFamily = fontFamily,
                color = Grey40
            )
        }
        if (languageState.code == language.code) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checkmark),
                contentDescription = "checked",
                tint = Green50
            )
        }
    }
}


