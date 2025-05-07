package com.example.bolis.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bolis.data.api.ServiceBuilder
import com.example.bolis.data.models.CatalogResponse
import com.example.bolis.data.models.ErrorResponse
import com.example.bolis.data.models.ItemDetailsResponse
import com.example.bolis.data.models.LikeItemRequest
import com.example.bolis.data.models.LikedItemsListResponse
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.MessageResponse
import com.example.bolis.data.models.ProfileResponse
import com.example.bolis.data.models.SearchResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import com.example.bolis.data.models.SuggestionsResponse
import com.example.bolis.data.models.VerificationRequest
import com.example.bolis.data.models.VerificationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class HomeViewModel(): ViewModel() {
    private var _catalogResponse: MutableLiveData<CatalogResponse?> = MutableLiveData()
    val catalogResponse: LiveData<CatalogResponse?> = _catalogResponse

    private var _suggestionsResponse: MutableLiveData<SuggestionsResponse?> = MutableLiveData()
    val suggestionsResponse: LiveData<SuggestionsResponse?> = _suggestionsResponse

    private var _searchResponse: MutableLiveData<SearchResponse?> = MutableLiveData()
    val searchResponse: LiveData<SearchResponse?> = _searchResponse

    private var _likedItemsResponse: MutableLiveData<LikedItemsListResponse?> = MutableLiveData()
    val likedItemsResponse: LiveData<LikedItemsListResponse?> = _likedItemsResponse

    private var _likedResponse: MutableLiveData<MessageResponse?> = MutableLiveData()
    val likedResponse: LiveData<MessageResponse?> = _likedResponse

    private var _itemDetailsResponse: MutableLiveData<ItemDetailsResponse?> = MutableLiveData()
    val itemDetailsResponse: LiveData<ItemDetailsResponse?> = _itemDetailsResponse

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
                    val errorMessage = if (throwable is HttpException) {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        try {
                            val json = JSONObject(errorBody ?: "")
                            json.getString("message")
                        } catch (e: Exception) {
                            "Something went wrong."
                        }
                    } else {
                        throwable.message ?: "An unknown error occurred."
                    }
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage))
                }
            )
        }
    }

    fun getSuggestions(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getSuggestions(token = token)
            }.fold(
                onSuccess = {
                    _suggestionsResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = if (throwable is HttpException) {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        try {
                            val json = JSONObject(errorBody ?: "")
                            json.getString("message")
                        } catch (e: Exception) {
                            "Something went wrong."
                        }
                    } else {
                        throwable.message ?: "An unknown error occurred."
                    }
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage))
                }
            )
        }
    }

    fun getLikedItems(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getLikedItemsList(token = token)
            }.fold(
                onSuccess = {
                    _likedItemsResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = if (throwable is HttpException) {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        try {
                            val json = JSONObject(errorBody ?: "")
                            json.getString("message")
                        } catch (e: Exception) {
                            "Something went wrong."
                        }
                    } else {
                        throwable.message ?: "An unknown error occurred."
                    }
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage))
                }
            )
        }
    }

    fun likeItem(token: String, itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.likeItem(token = token, itemId = LikeItemRequest(itemId = itemId))
            }.fold(
                onSuccess = {
                    _likedResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = if (throwable is HttpException) {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        try {
                            val json = JSONObject(errorBody ?: "")
                            json.getString("message")
                        } catch (e: Exception) {
                            "Something went wrong."
                        }
                    } else {
                        throwable.message ?: "An unknown error occurred."
                    }
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage))
                }
            )
        }
    }

    fun getItemDetails(token: String, itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getItemDetails(token = token, itemId = itemId)
            }.fold(
                onSuccess = {
                    _itemDetailsResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = if (throwable is HttpException) {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        try {
                            val json = JSONObject(errorBody ?: "")
                            json.getString("message")
                        } catch (e: Exception) {
                            "Something went wrong."
                        }
                    } else {
                        throwable.message ?: "An unknown error occurred."
                    }
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage))
                }
            )
        }
    }

    fun searchItem(token: String, query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.searchItems(token = token, query = query)
            }.fold(
                onSuccess = {
                    _searchResponse.postValue(it)
                },
                onFailure = { throwable ->
                    val errorMessage = if (throwable is HttpException) {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        try {
                            val json = JSONObject(errorBody ?: "")
                            json.getString("message")
                        } catch (e: Exception) {
                            "Something went wrong."
                        }
                    } else {
                        throwable.message ?: "An unknown error occurred."
                    }
                    _errorMessageResponse.postValue(ErrorResponse(errorMessage))
                }
            )
        }
    }
}