package com.dev_app.ecommercesales.`interface`.shoe

import com.dev_app.ecommercesales.models.ShoesProduct
import retrofit2.http.GET


interface ShoesApi {

    @GET("https://next.json-generator.com/EJJFgtGmY")
    suspend fun fetchAllShoesProducts(): List<ShoesProduct>
}
