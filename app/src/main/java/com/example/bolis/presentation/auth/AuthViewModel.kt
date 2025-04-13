package com.example.bolis.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(): ViewModel() {
    private var _signUpResponse: MutableLiveData<SignUpResponse?> = MutableLiveData()
    val signUpResponse: LiveData<SignUpResponse?> = _signUpResponse

    private var _logInResponse: MutableLiveData<LogInResponse?> = MutableLiveData()
    val logInResponse: LiveData<LogInResponse?> = _logInResponse

    private var _errorResponse: MutableLiveData<String?> = MutableLiveData()
    val errorResponse: LiveData<String?> = _errorResponse

    fun signUp(signUpRequest: SignUpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.signUp(signUpRequest)
            }.fold(
                onSuccess = {
                    _signUpResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorBody = (throwable as? retrofit2.HttpException)?.response()?.errorBody()?.string()
                    _errorResponse.postValue(errorBody ?: "Unknown error occurred")
                }
            )
        }
    }

    fun login(logInRequest: LogInRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.login(logInRequest)
            }.fold(
                onSuccess = {
                    _logInResponse.postValue(it)
                },
                onFailure = {
                    _errorResponse.postValue(it.message)
                }
            )
        }
    }
}