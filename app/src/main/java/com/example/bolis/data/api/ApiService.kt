package com.example.bolis.data.api

import com.example.bolis.data.models.CatalogResponse
import com.example.bolis.data.models.CategoriesListResponse
import com.example.bolis.data.models.DeliveryAddressResponse
import com.example.bolis.data.models.GiveProductRequest
import com.example.bolis.data.models.GiveProductResponse
import com.example.bolis.data.models.ItemDetailsResponse
import com.example.bolis.data.models.LikeItemRequest
import com.example.bolis.data.models.LikedItemsListResponse
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.MessageResponse
import com.example.bolis.data.models.MyGivesResponse
import com.example.bolis.data.models.NewsResponse
import com.example.bolis.data.models.ProfileResponse
import com.example.bolis.data.models.ProfileUpdateResponse
import com.example.bolis.data.models.SearchResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import com.example.bolis.data.models.SuggestionsResponse
import com.example.bolis.data.models.UpdatePasswordRequest
import com.example.bolis.data.models.VerificationRequest
import com.example.bolis.data.models.VerificationResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept: application/json")
    @POST("signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse

    @Headers("Accept: application/json")
    @POST("login")
    suspend fun login(
        @Body loginRequest: LogInRequest
    ): LogInResponse

    @Headers("Accept: application/json")
    @POST("verify-reset-code")
    suspend fun verify(
        @Body verifyRequest: VerificationRequest
    ): VerificationResponse

    @Headers("Accept: application/json")
    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @Multipart
    @Headers("Accept: application/json")
    @POST("profile-update")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Part first_name: MultipartBody.Part,
        @Part last_name: MultipartBody.Part,
        @Part email: MultipartBody.Part,
        @Part avatar_url: MultipartBody.Part?,
    ): ProfileUpdateResponse

    @Headers("Accept: application/json")
    @GET("give/products")
    suspend fun getCategories(
        @Header("Authorization") token: String
    ): CategoriesListResponse

    @Headers("Accept: application/json")
    @POST("give/products")
    suspend fun giveProducts(
        @Header("Authorization") token: String,
        @Body giveProductsBody: GiveProductRequest
    ): GiveProductResponse

    @Headers("Accept: application/json")
    @POST("update-password")
    suspend fun updatePassword(
        @Header("Authorization") token: String,
        @Body updatePasswordRequest: UpdatePasswordRequest
    ): MessageResponse

    @Headers("Accept: application/json")
    @GET("api/get-delivery-address")
    suspend fun getDeliveryAddress(
        @Header("Authorization") token: String
    ): DeliveryAddressResponse

    @Headers("Accept: application/json")
    @GET("api/suggestions")
    suspend fun getSuggestions(
        @Header("Authorization") token: String
    ): SuggestionsResponse

    @Multipart
    @Headers("Accept: application/json")
    @POST("give/products")
    suspend fun giveProductsWithImages(
        @Header("Authorization") token: String,
        @Part name: MultipartBody.Part,
        @Part categoryId: MultipartBody.Part,
        @Part description: MultipartBody.Part,
        @Part condition: MultipartBody.Part,
        @Part images: List<MultipartBody.Part>
    ): GiveProductResponse

    @Headers("Accept: application/json")
    @GET("home")
    suspend fun getCatalog(
        @Header("Authorization") token: String
    ): CatalogResponse

    @Headers("Accept: application/json")
    @GET("liked")
    suspend fun getLikedItemsList(
        @Header("Authorization") token: String
    ): LikedItemsListResponse

    @Headers("Accept: application/json")
    @POST("liked/add")
    suspend fun likeItem(
        @Header("Authorization") token: String,
        @Body itemId: LikeItemRequest
    ): MessageResponse

    @Headers("Accept: application/json")
    @GET("item/{itemId}")
    suspend fun getItemDetails(
        @Header("Authorization") token: String,
        @Path("itemId") itemId: Int
    ): ItemDetailsResponse

    @Headers("Accept: application/json")
    @GET("search")
    suspend fun searchItems(
        @Header("Authorization") token: String,
        @Query("query") query: String,
        @Query("category_id") categoryId: Int? = null,
        @Query("max_price") maxPrice: Double? = null,
        @Query("api") api: Int = 1 // важный параметр!
    ): SearchResponse

    @Headers("Accept: application/json")
    @GET("my-gives")
    suspend fun getMyGives(
        @Header("Authorization") token: String
    ): MyGivesResponse

    @Headers("Accept: application/json")
    @GET("stories")
    suspend fun getNews(
        @Header("Authorization") token: String
    ): NewsResponse
}