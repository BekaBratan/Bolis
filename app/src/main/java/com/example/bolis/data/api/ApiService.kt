package com.example.bolis.data.api

import com.example.bolis.data.models.LogInRequest
import com.example.bolis.data.models.LogInResponse
import com.example.bolis.data.models.SignUpRequest
import com.example.bolis.data.models.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse

    @POST("login")
    suspend fun login(
        @Body loginRequest: LogInRequest
    ): LogInResponse
}