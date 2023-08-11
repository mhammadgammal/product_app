package com.example.retrifitdemo

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsInterface {

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): Product


}