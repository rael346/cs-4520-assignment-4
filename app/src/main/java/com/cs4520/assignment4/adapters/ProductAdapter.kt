package com.cs4520.assignment4.adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.R
import com.cs4520.assignment4.model.Product

class ProductAdapter: ListAdapter<Product, ProductAdapter.ViewHolder>(ProductDiffCallback) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.productName)
        private val productImageView: ImageView = itemView.findViewById(R.id.productImage)
        private val productDateTextView: TextView = itemView.findViewById(R.id.productDate)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.productPrice)

        private var currentProduct: Product? = null

        fun bind(product: Product) {
            currentProduct = product
            productNameTextView.text = product.name
            productDateTextView.text = product.date
            productDateTextView.visibility = if (product.date == null) View.GONE else View.VISIBLE
            productPriceTextView.text = "$ " + product.price.toString()
//            when (product) {
//                is Product.Equipment -> {
//                    productImageView.setImageResource(R.drawable.equipment)
//                    itemView.setBackgroundResource(R.color.light_red)
//                }
//
//                is Product.Food -> {
//                    productImageView.setImageResource(R.drawable.food)
//                    itemView.setBackgroundResource(R.color.light_yellow)
//                }
//            }

            if (product.type == "Equipment") {
                productImageView.setImageResource(R.drawable.equipment)
                itemView.setBackgroundResource(R.color.light_red)
            }

            if (product.type == "Food") {
                productImageView.setImageResource(R.drawable.food)
                itemView.setBackgroundResource(R.color.light_yellow)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }
}
