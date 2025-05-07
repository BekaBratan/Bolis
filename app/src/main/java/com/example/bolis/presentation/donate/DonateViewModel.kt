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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class DonateViewModel : ViewModel() {

    private val _giveProductResponse = MutableStateFlow<GiveProductResponse?>(null)
    val giveProductResponse: StateFlow<GiveProductResponse?> = _giveProductResponse

    private val _categoriesResponse = MutableLiveData<CategoriesListResponse?>(null)
    val categoriesResponse: LiveData<CategoriesListResponse?> = _categoriesResponse

    private val _errorResponse = MutableStateFlow<String?>(null)
    val errorResponse: StateFlow<String?> = _errorResponse

    fun giveProduct(token: String, giveProductsBody: GiveProductRequest, images: List<File>) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val name = MultipartBody.Part.createFormData("name", giveProductsBody.name)
                val categoryId = MultipartBody.Part.createFormData("category_id", giveProductsBody.categoryId.toString())
                val condition = MultipartBody.Part.createFormData("condition", giveProductsBody.condition)
                val description = MultipartBody.Part.createFormData("description", giveProductsBody.description)

                val imageParts = images.map { imageFile ->
                    val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("images", imageFile.name, requestFile)
                }

                ServiceBuilder.api.giveProductsWithImages(
                    token = token,
                    name = name,
                    categoryId = categoryId,
                    description = description,
                    condition = condition,
                    images = imageParts
                )
            }.fold(
                onSuccess = { response ->
                    _giveProductResponse.value = response
                    _errorResponse.value = null
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)
                        ?.response()
                        ?.errorBody()
                        ?.string()
                        ?.takeIf { it.isNotBlank() }
                        ?: throwable.localizedMessage
                    _errorResponse.value = errorMessage
                }
            )
        }
    }

    fun getCategories(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getCategories(token = token)
            }.fold(
                onSuccess = { response ->
                    _categoriesResponse.postValue(response)
                    _errorResponse.value = null
                },
                onFailure = { throwable ->
                    val errorMessage = (throwable as? HttpException)
                        ?.response()
                        ?.errorBody()
                        ?.string()
                        ?.takeIf { it.isNotBlank() }
                        ?: throwable.localizedMessage
                    _errorResponse.value = errorMessage
                }
            )
        }
    }
}