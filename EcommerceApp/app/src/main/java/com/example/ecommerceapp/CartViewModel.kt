package com.example.ecommerceapp

import android.util.Log
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    //val cartItems = MutableLiveData<MutableList<Product>>(mutableListOf())
    val cartList = mutableListOf<Product>()
    fun addItemToCart(item: Product) {
        cartList.add(item)
    }
    fun clearCart() {
        cartList.clear()
    }
    fun getCartItemCount():Int{
        return cartList.size
    }
    fun removeItemFromCart(index:Int){
        cartList.removeAt(index)
    }

}