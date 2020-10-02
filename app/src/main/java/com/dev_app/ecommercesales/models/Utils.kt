package com.dev_app.ecommercesales.models



import com.dev_app.ecommercesales.database.AppDb
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    private lateinit var user: User

    fun getUser(appDb: AppDb): User {
        val getUser: List<User> = appDb.userDao().getUser()
        val listIterator: ListIterator<User> = getUser.listIterator()
        while (listIterator.hasNext()) {
            user = listIterator.next()
        }
        return user
    }

    companion object{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://next.json-generator.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

//https://next.json-generator.com/api/json/get/4J0IvXVBK