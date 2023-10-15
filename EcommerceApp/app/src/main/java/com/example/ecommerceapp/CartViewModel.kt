package com.example.ecommerceapp

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartViewModel : ViewModel() {
    private val _cartList = MutableLiveData<MutableList<CartItem>?>()
    val cartList: MutableLiveData<MutableList<CartItem>?> = _cartList

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    init {
        _cartList.value = mutableListOf()
    }

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("cart_preferences", Context.MODE_PRIVATE)
        loadCartFromSharedPreferences()
    }

    private fun loadCartFromSharedPreferences() {
        val cartJson = sharedPreferences.getString("cart", null)
        val cartItemList: MutableList<CartItem>? =
            gson.fromJson(cartJson, object : TypeToken<MutableList<CartItem>?>() {}.type)
        _cartList.value = cartItemList ?: mutableListOf() // Initialize with an empty list if null
    }

    private fun saveCartToSharedPreferences() {
        val cartJson = gson.toJson(_cartList.value)
        sharedPreferences.edit().putString("cart", cartJson).apply()
    }

    fun addItemToCart(cartItem: CartItem) {
        val product = cartItem.product
        val existingItem = _cartList.value?.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.count++
        } else {
            _cartList.value?.add(cartItem)
        }
        _cartList.postValue(_cartList.value) // Update LiveData
        saveCartToSharedPreferences()
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
                saveCartToSharedPreferences()
                _cartList.postValue(cartList)
            }
        }
    }

    fun clearCart() {
        _cartList.value?.clear()
        saveCartToSharedPreferences()
        _cartList.postValue(_cartList.value)
    }

    fun getCartItemCount(): Int {
        return _cartList.value?.size ?: 0
    }
}
