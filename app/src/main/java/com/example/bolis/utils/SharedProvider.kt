package com.example.bolis.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.bolis.data.models.SignUpRequest
import androidx.core.content.edit

class SharedProvider(private val context: Context) {
    private val _sharedToken = "AccessToken"
    private val _firstName = "FirstName"
    private val _lastName = "LastName"
    private val _password = "Password"
    private val _email = "Email"
    private val _phoneNumber = "PhoneNumber"
    private val _city = "City"
    private val _tokenType = "TokenType"
    private val _isAuthorized = "isAuthorized"

    private val preferences: SharedPreferences
        get() = context.getSharedPreferences("User", Context.MODE_PRIVATE)

    private val systemPreferences: SharedPreferences
        get() = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

    fun saveUser(user: SignUpRequest, firstName: String, lastName: String) {
        preferences.edit() {
            clear()
            putBoolean(_isAuthorized, true)
            putString(_email, user.email)
            putString(_password, user.password)
            putString(_firstName, firstName)
            putString(_lastName, lastName) }
    }

    fun getFirstName(): String {
        return "${preferences.getString(_firstName, "no_first_name")}"
    }

    fun getLastName(): String {
        return "${preferences.getString(_lastName, "no_last_name")}"
    }

    fun getEmail(): String {
        return "${preferences.getString(_email, "no_email")}"
    }

    fun getPassword(): String {
        return "${preferences.getString(_password, "no_password")}"
    }

    fun getPhoneNumber(): String {
        return "${preferences.getString(_phoneNumber, "no_phone_number")}"
    }

    fun getCity(): String {
        return "${preferences.getString(_city, "no_city")}"
    }

    fun setFirstName(firstName: String) {
        preferences.edit() { putString(_firstName, firstName) }
    }

    fun setLastName(lastName: String) {
        preferences.edit() { putString(_lastName, lastName) }
    }

    fun setEmail(email: String) {
        preferences.edit() { putString(_email, email) }
    }

    fun setPassword(password: String) {
        preferences.edit() { putString(_password, password) }
    }

    fun setPhoneNumber(phoneNumber: String) {
        preferences.edit() { putString(_phoneNumber, phoneNumber) }
    }

    fun setCity(city: String) {
        preferences.edit() { putString(_city, city) }
    }

    fun isAuthorized(): Boolean {
        return preferences.getBoolean(_isAuthorized, false)
    }

    fun saveToken(token: String) {
        preferences.edit() { putString(_sharedToken, token) }
    }

    fun getToken():String {
        return "${preferences.getString(_tokenType, "no_token_type")} ${preferences.getString(_sharedToken, "no_token")}"
    }

    fun setToken(token: String) {
        preferences.edit() {
            putString(_sharedToken, token)
            Log.d("AAA", "setToken: $token")
        }
    }

    fun saveLanguage(language: String) {
        systemPreferences.edit() { putString("Language", language) }
    }

    fun getLanguage(): String {
        return systemPreferences.getString("Language", "en").toString()
    }

    fun clearShared() {
        preferences.edit() { clear() }
    }

}