package com.example.bolis.presentation.news

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
import com.example.bolis.data.models.NewsResponse
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

class NewsViewModel(): ViewModel() {
    private var _newsResponse: MutableLiveData<NewsResponse?> = MutableLiveData()
    val newsResponse: LiveData<NewsResponse?> = _newsResponse

    private var _errorResponse: MutableLiveData<String?> = MutableLiveData()
    val errorResponse: LiveData<String?> = _errorResponse

    private var _errorMessageResponse: MutableLiveData<ErrorResponse?> = MutableLiveData()
    val errorMessageResponse: LiveData<ErrorResponse?> = _errorMessageResponse

    fun getNews(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                ServiceBuilder.api.getNews(token = token)
            }.fold(
                onSuccess = {
                    _newsResponse.postValue(it)
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