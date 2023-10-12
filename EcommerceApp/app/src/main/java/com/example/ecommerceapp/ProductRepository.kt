package com.example.ecommerceapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface ProductRepository {
    suspend fun fetchProducts() : Result<List<Product>>
    suspend fun fetchProduct(id: Int): Result<Product>
}

open class ProductRepositoryImplement  : ProductRepository{
    override suspend fun fetchProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO){
            try {
                val response = NetworkManager.restClient.getProducts()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Result.success(data)
                    }else if (response.code() == 401){
                        Result.failure(HttpException(response))
                    }
                    else {
                        Result.failure(NullPointerException("Response body is null"))
                    }
                } else {
                    Result.failure(HttpException(response))
                }
            }catch (e: Exception){
                Result.failure(e)
            }
        }
    }

    override suspend fun fetchProduct(id: Int): Result<Product> {
        return withContext(Dispatchers.IO){
            try{
                val response = NetworkManager.restClient.getProductDetails(id)
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Result.success(data)
                    }else if (response.code() == 401){
                        Result.failure(HttpException(response))
                    }
                    else {
                        Result.failure(NullPointerException("Response body is null"))
                    }
                } else {
                    Result.failure(HttpException(response))
                }
            }catch (e: Exception){
                Result.failure(e)
            }
        }
    }

}