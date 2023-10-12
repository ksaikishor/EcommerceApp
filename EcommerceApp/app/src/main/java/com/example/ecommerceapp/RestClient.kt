package com.example.ecommerceapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestClient {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id : Int):Response<Product>
}