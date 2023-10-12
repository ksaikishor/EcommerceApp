package com.example.ecommerceapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItemList: MutableList<CartItem>,private val itemClick : ItemOnClick) :
    RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItemList[position]
        val product = cartItem.product
        Log.i("product is", "$product")
        holder.bind(cartItem)
    }
    override fun getItemCount(): Int {
        return cartItemList.size
    }

    fun removeProduct(index: Int) {
        cartItemList.removeAt(index)
        notifyItemRemoved(index)
    }

}

