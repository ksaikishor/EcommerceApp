package com.example.ecommerceapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewDetailModelFactory(private val productRepository: ProductRepositoryImplement) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}