package com.dev_app.ecommercesales.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev_app.ecommercesales.models.Products


@Database(entities = [Products::class], version = 2)
abstract class SearchDb : RoomDatabase() {
    abstract fun searcDao(): SearchDao
}