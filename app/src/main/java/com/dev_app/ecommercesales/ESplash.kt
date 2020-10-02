package com.dev_app.ecommercesales

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View

class ESplash : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000L
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_splash)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        sharedPreferences = applicationContext.getSharedPreferences(
            "ecommercesales",
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        Log.d("token", sharedPreferences.getString("token", "")!!)

        Handler().postDelayed(
            {
                when {
                    sharedPreferences.getString("token", "")!! == "logged" -> {
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    else -> {
                        val i = Intent(this, LoginActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                }
            }, SPLASH_TIME_OUT
        )
    }
}