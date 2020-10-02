package com.dev_app.ecommercesales.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dev_app.ecommercesales.models.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): List<User>

    @Query(
        "SELECT * FROM user WHERE email LIKE :email AND " +
                "password LIKE :password LIMIT 1"
    )
    fun findByName(email: String, password: String): User

    @Query("UPDATE user SET fname =:fname, lname= :lname,age=:age,email=:email,password=:password,gender=:gender")
    fun updateUser(
        fname: String,
        lname: String,
        age: String,
        gender: String,
        email: String,
        password: String
    )

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}