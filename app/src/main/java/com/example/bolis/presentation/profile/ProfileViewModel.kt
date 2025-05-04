package com.example.bolis.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.models.DeliveryAddressResponse
import com.example.bolis.data.models.ErrorResponse
import com.example.bolis.data.models.MessageResponse
import com.example.bolis.data.models.ProfileResponse
import com.example.bolis.data.models.ProfileUpdateResponse
import com.example.bolis.data.models.UpdatePasswordRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class ProfileViewModel(): ViewModel() {
    private var _profileResponse: MutableLiveData<ProfileResponse?> = MutableLiveData()
    val profileResponse: LiveData<ProfileResponse?> = _profileResponse

    private var _profileUpdateResponse: MutableLiveData<ProfileUpdateResponse?> = MutableLiveData()
    val profileUpdateResponse: LiveData<ProfileUpdateResponse?> = _profileUpdateResponse

    private var _deliveryAddressResponse: MutableLiveData<DeliveryAddressResponse?> = MutableLiveData()
    val deliveryAddressResponse: LiveData<DeliveryAddressResponse?> = _deliveryAddressResponse

    private var _messageResponse: MutableLiveData<MessageResponse?> = MutableLiveData()
    val messageResponse: LiveData<MessageResponse?> = _messageResponse

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

    fun updateProfile(token: String, profileBody: ProfileResponse, imageFile: File?) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val first_name = MultipartBody.Part.createFormData("first_name", profileBody.firstName)
                val last_name = MultipartBody.Part.createFormData("last_name", profileBody.lastName)
                val email = MultipartBody.Part.createFormData("email", profileBody.email)
                var imagePart: MultipartBody.Part? = null

                if (imageFile!=null) {
                    val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                    imagePart = MultipartBody.Part.createFormData("avatar_url", imageFile.name, requestFile)
                }

                ServiceBuilder.api.updateProfile(
                    token = token,
                    first_name = first_name,
                    last_name = last_name,
                    avatar_url = imagePart,
                    email = email,
                )
            }.fold(
                onSuccess = {
                    _profileUpdateResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }

    fun updatePassword(
        token: String,
        updatePasswordRequest: UpdatePasswordRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.updatePassword(token = token, updatePasswordRequest = updatePasswordRequest)
            }.fold(
                onSuccess = {
                    _messageResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }

    fun getDeliveryAddress(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getDeliveryAddress(token = token)
            }.fold(
                onSuccess = {
                    _deliveryAddressResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)?.response()?.errorBody()?.string()
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage.toString()))
                }
            )
        }
    }
}