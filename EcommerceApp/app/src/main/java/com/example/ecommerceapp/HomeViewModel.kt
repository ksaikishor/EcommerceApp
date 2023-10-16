package com.example.ecommerceapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepository: ProductRepositoryImplement) : ViewModel() {
    val productList = MutableLiveData<List<Product>?>()

    val isLoading = MutableLiveData(false)
    private val errorLiveDataProducts = MutableLiveData(false)

    fun fetchProducts(){
        isLoading.value = true
        viewModelScope.launch{
            val result = productRepository.fetchProducts()
            result.onSuccess {
                productList.postValue(it)
            }
            result.onFailure {
                errorLiveDataProducts.postValue(true)
            }
            isLoading.value = false
        }

    }


}