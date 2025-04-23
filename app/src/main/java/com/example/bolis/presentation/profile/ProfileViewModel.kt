package com.example.bolis.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.models.ErrorResponse
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.ProfileResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import com.example.bolis.data.models.VerificationRequest
import com.example.bolis.data.models.VerificationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel(): ViewModel() {
    private var _profileResponse: MutableLiveData<ProfileResponse?> = MutableLiveData()
    val profileResponse: LiveData<ProfileResponse?> = _profileResponse

    private var _errorResponse: MutableLiveData<String?> = MutableLiveData()
    val errorResponse: LiveData<String?> = _errorResponse

    private var _errorMessageResponse: MutableLiveData<ErrorResponse?> = MutableLiveData()
    val errorMessageResponse: LiveData<ErrorResponse?> = _errorMessageResponse

    fun getProfile(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getProfile(token = token)
            }.fold(
                onSuccess = {
                    _profileResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }

    fun updateProfile(token: String, profileBody: ProfileResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.updateProfile(token = token, profileBody = profileBody)
            }.fold(
                onSuccess = {
                    _errorResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }
}