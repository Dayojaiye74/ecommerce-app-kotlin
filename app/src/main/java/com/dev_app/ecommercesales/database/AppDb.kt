package com.dev_app.ecommercesales.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev_app.ecommercesales.database.UserDao
import com.dev_app.ecommercesales.models.User

@Database(entities = [User::class], version = 2)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}