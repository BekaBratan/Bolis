package com.example.bolis.data.api

import com.example.bolis.data.models.CatalogResponse
import com.example.bolis.data.models.CategoriesListResponse
import com.example.bolis.data.models.GiveProductRequest
import com.example.bolis.data.models.GiveProductResponse
import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.ProfileResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import com.example.bolis.data.models.VerificationRequest
import com.example.bolis.data.models.VerificationResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @Headers("Accept: application/json")
    @POST("profile-update")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body profileBody: ProfileResponse
    ): String

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

    @Multipart
    @Headers("Accept: application/json")
    @POST("give/products")
    suspend fun giveProductsWithImages(
        @Part("name") name: RequestBody,
        @Part("description") desc: RequestBody,
        @Part("category_id") categoryId: RequestBody,
        @Part("condition") condition: RequestBody,
        @Part images: List<MultipartBody.Part>
    ): GiveProductResponse

    @Headers("Accept: application/json")
    @GET("home")
    suspend fun getCatalog(
        @Header("Authorization") token: String
    ): CatalogResponse
}