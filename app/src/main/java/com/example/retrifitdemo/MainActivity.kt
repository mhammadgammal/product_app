package com.example.retrifitdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrifitdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var productsList: List<Product> = listOf()
    private val productsAdapter: ProductsAdapter by lazy {
        ProductsAdapter(this,
            object : OnProductClickListener {
                override fun onProductClickListener(position: Int) {
                    val intent = Intent(this@MainActivity, ProductDetailsActivity::class.java)
                        .apply {
                            putExtra(PRODUCT_ID, productsList[position].id)
                        }
                    startActivity(intent)
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProducts()
        binding.productsRecyclerView.adapter = productsAdapter
    }

    @SuppressLint("notifyDataSetChanged")
    private fun getProducts() =
        CoroutineScope(Dispatchers.IO).launch {
            val products = ProductsClient.retrofitService.getAllProducts().products
            withContext(Main) {
                productsAdapter.products = products
                productsAdapter.notifyDataSetChanged()
                binding.productsRecyclerView.adapter = productsAdapter
            }
        }


    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
    }
}



