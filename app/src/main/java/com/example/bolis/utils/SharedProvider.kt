package com.example.bolis.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedProvider(private val context: Context) {
    private val sharedToken = "AccessToken"
    private val tokenType = "TokenType"
    private val isAuthorized = "isAuthorized"

    private val preferences: SharedPreferences
        get() = context.getSharedPreferences("User", Context.MODE_PRIVATE)

    private val systemPreferences: SharedPreferences
        get() = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

//    fun saveUser(user: AuthorizationResponse) {
//        val editor = preferences.edit()
//        editor.clear()
//        editor.putBoolean("isAuthorized", true)
//        editor.apply()
//        saveToken(user.access_token.toString())
//    }

    fun isAuthorized(): Boolean {
        return preferences.getBoolean(isAuthorized, false)
    }

    fun saveToken(token: String) {
        preferences.edit().putString(sharedToken, token).apply()
    }

    fun getToken():String {
        return "${preferences.getString(tokenType, "without_token_type")} ${preferences.getString(sharedToken, "without_token")}"
    }

    fun setToken(token: String) {
        val editor = preferences.edit()
        editor.putString(sharedToken, token)
        Log.d("AAA", "setToken: $token")
        editor.apply()
    }

    fun saveLanguage(language: String) {
        systemPreferences.edit().putString("Language", language).apply()
    }

    fun getLanguage(): String {
        return systemPreferences.getString("Language", "en").toString()
    }

    fun clearShared() {
        preferences.edit().clear().apply()
    }

}