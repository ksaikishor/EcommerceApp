package com.example.ecommerceapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val okHttpClient = OkHttpClient.Builder().build()
    private const val BASE_URL = "https://fakestoreapi.com/"
    var restClient: RestClient

    init {
        restClient = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestClient::class.java)
    }
}