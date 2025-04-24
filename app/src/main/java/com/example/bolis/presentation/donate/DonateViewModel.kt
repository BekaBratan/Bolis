package com.example.bolis.presentation.donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.models.CategoriesListResponse
import com.example.bolis.data.models.ErrorResponse
import com.example.bolis.data.models.GiveProductRequest
import com.example.bolis.data.models.GiveProductResponse
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

class DonateViewModel(): ViewModel() {
    private var _giveProductResponse: MutableLiveData<GiveProductResponse?> = MutableLiveData()
    val giveProductResponse: LiveData<GiveProductResponse?> = _giveProductResponse

    private var _categoriesResponse: MutableLiveData<CategoriesListResponse?> = MutableLiveData()
    val categoriesResponse: LiveData<CategoriesListResponse?> = _categoriesResponse

    private var _errorResponse: MutableLiveData<String?> = MutableLiveData()
    val errorResponse: LiveData<String?> = _errorResponse

    private var _errorMessageResponse: MutableLiveData<ErrorResponse?> = MutableLiveData()
    val errorMessageResponse: LiveData<ErrorResponse?> = _errorMessageResponse

    fun giveProduct(token: String, giveProductsBody: GiveProductRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.giveProducts(token = token, giveProductsBody = giveProductsBody)
            }.fold(
                onSuccess = {
                    _giveProductResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }

    fun getCategories(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getCategories(token = token)
            }.fold(
                onSuccess = {
                    _categoriesResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }
}