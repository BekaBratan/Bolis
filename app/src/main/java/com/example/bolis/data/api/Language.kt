package com.example.bolis.data.api

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat

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
var isLanguageChanged by mutableStateOf(false)

@Composable
fun ObserveLanguageChange(context: Context, appLocaleManager: AppLocaleManager) {
    if (!isLanguageChanged) {
        val currentLanguageCode = appLocaleManager.getLanguageCode(context)
        languageState = appLanguages.find { it.code == currentLanguageCode } ?: appLanguages[0]
        isLanguageChanged = true
    }

    LaunchedEffect(languageState) {
        appLocaleManager.changeLanguage(context, languageState.code)
    }
}

class AppLocaleManager {

    fun changeLanguage(context: Context, languageCode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }

    fun getLanguageCode(context: Context,): String {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                ?.applicationLocales
                ?.get(0)
        } else {
            AppCompatDelegate.getApplicationLocales().get(0)
        }
        return locale?.language ?: getDefaultLanguageCode()
    }

    private fun getDefaultLanguageCode(): String {
        return appLanguages.first().toString()
    }
}