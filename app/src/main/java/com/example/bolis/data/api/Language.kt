package com.example.bolis.data.api

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bolis.utils.SharedProvider
import java.util.Locale

data class Language(
    val code: String,
    val displayLanguage: String
)

val appLanguages = listOf(
    Language("kk", "Kazakh"),
    Language("ru", "Russian"),
    Language("en", "English"), // default language
)

var languageState by mutableStateOf(appLanguages[0])

fun systemLanguageChange(context: Context, languageCode: String) {
    languageState = appLanguages.find { it.code == languageCode } ?: languageState
    SharedProvider(context).saveLanguage(languageCode)
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}
