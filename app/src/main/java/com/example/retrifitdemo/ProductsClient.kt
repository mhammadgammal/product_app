package com.example.retrifitdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ProductsClient {
    private const val BASE_URL = "https://dummyjson.com/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: ProductsInterface by lazy {
        retrofit.create(ProductsInterface::class.java)
    }

}