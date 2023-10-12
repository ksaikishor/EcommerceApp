package com.example.ecommerceapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartViewHolder(itemView : View,itemOnClick: ItemOnClick):RecyclerView.ViewHolder(itemView) {
    init {
        itemView.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            itemOnClick.itemClick(adapterPosition)
        }
    }
    fun bind(product: Product) {
        itemView.findViewById<TextView>(R.id.productDescription).text = product.description
        itemView.findViewById<TextView>(R.id.count).text = "Count :"+ product.rating.count.toString()
        val image = itemView.findViewById<ImageView>(R.id.productImage)
        itemView.findViewById<TextView>(R.id.price).text= "Price :$ "+product.price
        Glide.with(itemView).load(product.image).into(image)
    }
}