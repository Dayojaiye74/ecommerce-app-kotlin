package com.dev_app.ecommercesales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.dev_app.ecommercesales.database.AppDatabase
import com.dev_app.ecommercesales.database.CartModel
import com.dev_app.ecommercesales.database.ProductFromDatabase
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

//        if(new DarkModePrefManager(this).isNightMode()){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            db.productDao().insertAll(ProductFromDatabase(null, "Socks - one dozen", 1.99))
            val products = db.productDao().getAll()

            val cart = db.cartDao()
            cart.insertAll(CartModel(null, "Test product", 12.99, 3))

            val allCartItems = cart.getAll()

            uiThread {

                Log.d("daniel", "products size? ${products.size} ${products[0].title}")

                allCartItems.forEach {
                    Log.d("daniel", "item in cart: ${it.title} ${it.price}")
                }

            }
        }

        bottomNavigationView.setupWithNavController(shopNavHostFragment.findNavController())
    }
}


