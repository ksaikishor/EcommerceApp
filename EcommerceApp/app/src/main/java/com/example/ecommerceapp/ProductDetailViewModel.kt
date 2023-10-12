package com.example.ecommerceapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductDetailViewModel(private val productRepository: ProductRepositoryImplement) : ViewModel() {

    val isLoadingProduct = MutableLiveData(false)
    val product = MutableLiveData<Product>()
    val errorLiveDataProduct = MutableLiveData(false)

    fun fetchProduct(id : Int){
        isLoadingProduct.value = true
        viewModelScope.launch {
            val result= productRepository.fetchProduct(id)
            result.onSuccess {
                product.postValue(it)
            }
            result.onFailure {
                errorLiveDataProduct.postValue(true)
            }
            isLoadingProduct.value = false
        }

    }
}