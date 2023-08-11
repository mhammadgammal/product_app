package com.example.retrifitdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.retrifitdemo.databinding.ProductDetailItemBinding

class ProductDetailsAdapter : RecyclerView.Adapter<ProductDetailsAdapter.ProductsViewHolder>() {
    var productImages = listOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder =
        ProductsViewHolder(
            ProductDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.binding.productDetailImageView.setImageResource(R.drawable.loading_animation)
        holder.binding.productDetailImageView.load(productImages[position]) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }

    override fun getItemCount(): Int = productImages.size

    class ProductsViewHolder(val binding: ProductDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}