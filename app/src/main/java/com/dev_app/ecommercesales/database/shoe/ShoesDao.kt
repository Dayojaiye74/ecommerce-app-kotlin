package com.dev_app.ecommercesales.database.shoe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query



@Dao
interface ShoesDao {

    @Query("SELECT * FROM ShoesFromDatabase")
    fun getAll(): List<ShoesFromDatabase>

    @Query("SELECT * FROM ShoesFromDatabase WHERE title LIKE :term")
    fun searchFor(term: String): List<ShoesFromDatabase>

    @Insert
    fun insertAll(vararg shoesProducts: ShoesFromDatabase)
}