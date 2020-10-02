package com.dev_app.ecommercesales.viewModel

import com.dev_app.ecommercesales.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("api/json/get/4J0IvXVBK")
    suspend fun getProducts(): Response<List<Products>>
}