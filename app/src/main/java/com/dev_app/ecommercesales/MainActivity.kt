package com.dev_app.ecommercesales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dev_app.ecommercesales.models.ConnectionClass
import com.dev_app.ecommercesales.ui.MainFragment
import com.dev_app.ecommercesales.ui.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val fragment1: Fragment = MainFragment()
    private val fragment2: Fragment = SearchFragment()
    private val fragment3: Fragment = SettingsFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager
    private lateinit var menu: Menu

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    val fragmentTransaction =
                        supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, fragment1)
                    fragmentTransaction.addToBackStack("1")
                    fragmentTransaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.searchFragment -> {
                    val fragmentTransaction3 =
                        supportFragmentManager.beginTransaction()
                    fragmentTransaction3.replace(R.id.fragment_container, fragment2)
                    fragmentTransaction3.addToBackStack("2")
                    fragmentTransaction3.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.settingsFragment -> {
                    val fragmentTransaction4 =
                        supportFragmentManager.beginTransaction()
                    fragmentTransaction4.replace(R.id.fragment_container, fragment3)
                    fragmentTransaction4.addToBackStack("3")
                    fragmentTransaction4.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    @Suppress("UNUSED_VARIABLE")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        var fragmentContainer: LinearLayout = findViewById(R.id.fragment_container)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        ConnectionClass() //..connectivity manager

        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Buy For Me"
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        val fragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment1)
        fragmentTransaction.addToBackStack("1")
        fragmentTransaction.commit()

        fragmentManager.addOnBackStackChangedListener {
            when {
                getVisibleFragment() === fragment1 -> {
                    Log.d("fragment1", "isvisible")
                    bottomNavigationView.menu.getItem(0).isChecked = true
                }
                getVisibleFragment() === fragment2 -> {
                    Log.d("fragment2", "isvisible")
                    bottomNavigationView.menu.getItem(1).isChecked = true
                }
                getVisibleFragment() === fragment3 -> {
                    Log.d("fragment3", "isvisible")
                    bottomNavigationView.menu.getItem(2).isChecked = true
                }
                else -> {
                    bottomNavigationView.menu.getItem(0).isChecked = true
                }
            }
        }
    }

    private fun getVisibleFragment(): Fragment? {
        val fragmentManager =
            this.supportFragmentManager
        val fragments =
            fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment != null && fragment.isVisible) return fragment
        }
        return null
    }

    override fun onBackPressed() {
        if (getVisibleFragment() === fragment1) {
            Log.d("fragment1", "isvisible")
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.alert) {
            startActivity(Intent(this@MainActivity, MyCart::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}