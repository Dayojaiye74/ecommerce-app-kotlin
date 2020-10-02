package com.dev_app.ecommercesales.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dev_app.ecommercesales.models.Products


@Dao
interface ProductsDao {
    @Query("SELECT * FROM products")
    fun getProducts(): List<Products>

    @Insert
    fun insertProducts(list: List<Products>)

    @Insert
    fun insertProduct(products: Products)

    @Delete
    fun deleteProduct(products: Products)
}