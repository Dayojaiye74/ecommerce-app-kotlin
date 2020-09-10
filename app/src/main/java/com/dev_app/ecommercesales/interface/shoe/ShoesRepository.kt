package com.dev_app.ecommercesales.`interface`.shoe

import com.dev_app.ecommercesales.models.Product
import com.dev_app.ecommercesales.models.ShoesProduct
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

    class ShoesRepository {

        private fun retrofit(): ShoesApi {
            return Retrofit.Builder()
                .baseUrl("https://finepointmobile.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(ShoesApi::class.java)
        }

        suspend fun fetchAllShoesRetrofit(): List<ShoesProduct> {
            return retrofit().fetchAllShoesProducts()
        }

        fun getAllShoesProducts(): Single<List<ShoesProduct>> {
            return Single.create<List<ShoesProduct>> {
                it.onSuccess(fetchShoes())
            }
        }

        suspend fun searchForShoesProducts(term: String): List<ShoesProduct> {
            return fetchAllShoesRetrofit().filter { it.title.contains(term, true) }
        }

        fun getShoesByName(name: String): Single<ShoesProduct> {
            return Single.create<ShoesProduct> {
                val shoesProduct = fetchShoes().first { it.title == name }
                it.onSuccess(shoesProduct)
            }
        }

        fun fetchShoes(): List<ShoesProduct> {
            val json =
                URL("https://next.json-generator.com/api/json/get/EJJFgtGmY").readText()
            return Gson().fromJson(json, Array<ShoesProduct>::class.java).toList()
        }
    }
