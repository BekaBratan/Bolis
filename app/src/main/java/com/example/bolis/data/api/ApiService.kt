package com.example.bolis.data.api

import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.ProfileResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import com.example.bolis.data.models.VerificationRequest
import com.example.bolis.data.models.VerificationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

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
}