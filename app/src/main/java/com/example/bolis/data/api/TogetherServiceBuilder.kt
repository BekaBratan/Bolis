package com.example.bolis.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.example.bolis.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory

object TogetherServiceBuilder {
    private const val BASE_URL = "https://api.together.xyz/"
    private const val API_KEY = BuildConfig.TOGETHER_API_KEY

    val apiService: TogetherApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $API_KEY")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }.build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TogetherApiService::class.java)
    }
}