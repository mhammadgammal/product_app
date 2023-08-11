package com.example.retrifitdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrifitdemo.databinding.ActivityProductDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private val detailsAdapter: ProductDetailsAdapter by lazy { ProductDetailsAdapter() }
    private var productImages = listOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productId = intent.extras?.getInt(MainActivity.PRODUCT_ID, -1)!!
        @SuppressLint("NotifyDataSetChanged")
        if (productId != -1) {
            CoroutineScope(IO).launch {
                val product = ProductsClient.retrofitService.getProductById(productId)
                withContext(Main) {
                    productImages = product.images
                    binding.brandTextview.text = product.brand
                    binding.titleTextview.text = product.title
                    binding.ratingBar.rating = product.rating.toFloat()

                    detailsAdapter.apply {
                        this.productImages = this@ProductDetailsActivity.productImages
                        notifyDataSetChanged()
                    }
                    binding.productImageRecyclerView.adapter = detailsAdapter
                }
            }


        } else {
            Toast.makeText(this, "An Error Occurred", Toast.LENGTH_SHORT).show()
        }

    }


}