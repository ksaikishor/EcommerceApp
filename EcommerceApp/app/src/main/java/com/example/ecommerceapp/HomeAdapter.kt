package com.example.ecommerceapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val productList : List<Product>,private var itemClick: ItemOnClick) : RecyclerView.Adapter<HomeDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return HomeDataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: HomeDataViewHolder, position: Int) {
        val products = productList[position]
        holder.bind(products)
        Log.i("Image url is ", "${products.image}")

        holder.itemView.setOnClickListener {
            val productId = products.id
            itemClick?.itemClick(productId)
        }
    }
}