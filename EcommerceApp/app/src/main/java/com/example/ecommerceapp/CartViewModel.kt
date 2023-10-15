package com.example.ecommerceapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val _cartList = MutableLiveData<MutableList<CartItem>?>()
    val cartList: MutableLiveData<MutableList<CartItem>?> = _cartList

    init {
        _cartList.value = mutableListOf()
    }

    fun addItemToCart(product: Product) {
        val existingItem = _cartList.value?.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.count++
        } else {
            _cartList.value?.add(CartItem(product, 1))
        }
        _cartList.postValue(_cartList.value)
    }

    fun removeItemFromCart(index: Int) {
        val cartList = _cartList.value
        if (index in 0 until (cartList?.size ?: 0)) {
            val item = cartList?.get(index)
            if (item != null) {
                if (item.count > 1) {
                    item.count--
                } else {
                    cartList.removeAt(index)
                }
                _cartList.postValue(cartList)
            }
        }
    }

    fun clearCart() {
        _cartList.value?.clear()
        _cartList.postValue(_cartList.value)
    }

    fun getCartItemCount(): Int {
        return _cartList.value?.size ?: 0
    }
}
