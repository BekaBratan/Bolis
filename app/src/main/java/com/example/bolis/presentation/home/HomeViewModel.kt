package com.example.bolis.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.models.CatalogResponse
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

class HomeViewModel(): ViewModel() {
    private var _catalogResponse: MutableLiveData<CatalogResponse?> = MutableLiveData()
    val catalogResponse: LiveData<CatalogResponse?> = _catalogResponse

    private var _errorResponse: MutableLiveData<String?> = MutableLiveData()
    val errorResponse: LiveData<String?> = _errorResponse

    private var _errorMessageResponse: MutableLiveData<ErrorResponse?> = MutableLiveData()
    val errorMessageResponse: LiveData<ErrorResponse?> = _errorMessageResponse

    fun getCatalog(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getCatalog(token = token)
            }.fold(
                onSuccess = {
                    _catalogResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }
}