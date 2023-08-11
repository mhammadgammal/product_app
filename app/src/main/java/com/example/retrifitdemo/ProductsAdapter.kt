package com.example.retrifitdemo


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.retrifitdemo.databinding.ProductItemBinding


class ProductsAdapter(private val context: Context, private val listener: OnProductClickListener): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    var products: List<Product> = listOf()
    init {
        Log.d(TAG, TAG)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder = ProductsViewHolder(
        ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        listener
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.binding.titleTextView.text = products[position].title
        holder.binding.brandTextView.text = products[position].brand
        holder.binding.priceBeforeDiscountTextView.text = products[position].price.toString()
        holder.binding.descriptionTextView.text = products[position].description
        holder.binding.discountPercentageTextView.text =
            products[position].discountPercentage.toString() + context.getString(R.string.off)
        holder.binding.priceAfterDiscountTextView.text = String.format("%.2f", (products[position].price - products[position].discountPercentage))
        if(products[position].thumbnail.isEmpty()){
            holder.binding.thumbnailImageView.setImageResource(R.drawable.ic_broken_image)
        }
        else{
            holder.binding.thumbnailImageView.setImageResource(R.drawable.loading_animation)
            holder.binding.thumbnailImageView.load(products[position].thumbnail) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    override fun getItemCount() = products.size

    class ProductsViewHolder(val binding: ProductItemBinding,listener: OnProductClickListener): RecyclerView.ViewHolder(binding.root){
        init {
            binding.productCard.setOnClickListener {
                listener.onProductClickListener(adapterPosition)
            }
        }
    }
}
interface OnProductClickListener{
    fun onProductClickListener(position: Int)
}
private const val TAG = "ProductsAdapter"