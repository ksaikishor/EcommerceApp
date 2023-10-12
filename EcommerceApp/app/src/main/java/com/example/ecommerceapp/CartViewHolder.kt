package com.example.ecommerceapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartViewHolder(itemView: View, itemOnClick: ItemOnClick) : RecyclerView.ViewHolder(itemView) {
    private fun showDeleteConfirmationDialog(adapterPosition: Int, itemOnClick: ItemOnClick) {
        val alertDialogBuilder = AlertDialog.Builder(itemView.context)
        alertDialogBuilder.setTitle("Confirm Deletion")
        alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            itemOnClick.itemClick(adapterPosition)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.create().show()
    }
    init {
        itemView.findViewById<Button>(R.id.deleteButton).setOnClickListener {
            showDeleteConfirmationDialog(adapterPosition,itemOnClick)
        }
    }

    fun bind(cartItem: CartItem) {
        val product = cartItem.product
        itemView.findViewById<TextView>(R.id.productDescription).text = product.description
        itemView.findViewById<TextView>(R.id.count).text = "Count: " + cartItem.count
        val image = itemView.findViewById<ImageView>(R.id.productImage)
        itemView.findViewById<TextView>(R.id.price).text = "Price: $ " + product.price
        Glide.with(itemView).load(product.image).into(image)
    }
}
