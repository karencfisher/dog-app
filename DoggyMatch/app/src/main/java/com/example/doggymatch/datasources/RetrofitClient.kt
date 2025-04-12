package com.example.doggymatch.datasources

import com.example.doggymatch.BuildConfig
import com.example.doggymatch.apis.RescueApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/vnd.api+json")
                .addHeader("Authorization", BuildConfig.RESCUE_API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.rescuegroups.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api: RescueApiService = retrofit.create(RescueApiService::class.java)

}
