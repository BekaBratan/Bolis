package com.example.bolis.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.models.ErrorResponse
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import com.example.bolis.data.models.VerificationRequest
import com.example.bolis.data.models.VerificationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(): ViewModel() {
    private var _signUpResponse: MutableLiveData<SignUpResponse?> = MutableLiveData()
    val signUpResponse: LiveData<SignUpResponse?> = _signUpResponse

    private var _logInResponse: MutableLiveData<LogInResponse?> = MutableLiveData()
    val logInResponse: LiveData<LogInResponse?> = _logInResponse

    private var _verifyResponse: MutableLiveData<VerificationResponse?> = MutableLiveData()
    val verifyResponse: LiveData<VerificationResponse?> = _verifyResponse

    private var _errorResponse: MutableLiveData<String?> = MutableLiveData()
    val errorResponse: LiveData<String?> = _errorResponse

    private var _errorMessageResponse: MutableLiveData<ErrorResponse?> = MutableLiveData()
    val errorMessageResponse: LiveData<ErrorResponse?> = _errorMessageResponse

    fun signUp(signUpRequest: SignUpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.signUp(signUpRequest)
            }.fold(
                onSuccess = {
                    _signUpResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? retrofit2.HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
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
                onFailure = { throwable ->
                    val errorMessage = (throwable as? retrofit2.HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }

    fun verify(verificationRequest: VerificationRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.verify(verificationRequest)
            }.fold(
                onSuccess = {
                    _verifyResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? retrofit2.HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }
}