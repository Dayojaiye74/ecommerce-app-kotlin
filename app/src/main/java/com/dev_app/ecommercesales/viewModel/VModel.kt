package com.dev_app.ecommercesales.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.dev_app.ecommercesales.database.SearchDb
import com.dev_app.ecommercesales.models.Products
import com.dev_app.ecommercesales.models.Utils
import kotlinx.coroutines.*
import java.lang.Exception

class VModel : ViewModel() {
    lateinit var searchDb: SearchDb
    lateinit var vcontext: Context

    private val productsData: MutableLiveData<List<Products>> by lazy {
        MutableLiveData<List<Products>>().also {
            loadProducts()
        }
    }

    fun getProducts(context: Context): LiveData<List<Products>> {
        vcontext = context
        return productsData
    }

    private fun loadProducts() {
        val service = Utils.retrofit.create(Api::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getProducts()
            withContext(Dispatchers.Main) {
                try {
                    if (call.isSuccessful) {
                        productsData.value = call.body()
                        searchDb = Room
                            .databaseBuilder(vcontext, SearchDb::class.java, "Search")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                        searchDb.searcDao().insertAllProducts(call.body()!!)
                    } else {
                        Log.d("error", "occurred")
                    }
                } catch (e: Exception) {
                    Log.d("load-error", e.toString())
                }
            }
        }
    }
}