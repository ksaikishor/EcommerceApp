package com.example.ecommerceapp

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeDataViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
    fun bind(product: Product){
        val imageView = itemView.findViewById<ImageView>(R.id.productImg)
        val titleView = itemView.findViewById<TextView>(R.id.productTitle)
        if(!product.image.isNullOrEmpty()) {
            Glide.with(itemView.context).load(product.image).into(imageView)
            titleView.text = product.title
        }
    }

}