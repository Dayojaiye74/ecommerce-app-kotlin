package com.dev_app.ecommercesales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.dev_app.ecommercesales.database.AppDb
import com.dev_app.ecommercesales.mail.AppExecutors
import com.dev_app.ecommercesales.models.User
import com.dev_app.ecommercesales.models.Utils
import kotlinx.android.synthetic.main.activity_edit_profile.*

@Suppress("DEPRECATION", "PrivatePropertyName")
class EditProfileActivity : AppCompatActivity() {

    private lateinit var appDb: AppDb
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val PREFS = "ecommercesales"
    private var utils: Utils = Utils()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP) //....

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        appDb = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "User"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

        sharedPreferences = getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        male.setOnClickListener {
            maleGender()
        }
        female.setOnClickListener {
            femaleGender()
        }

        try {
            firstname.setText(utils.getUser(appDb).fname.toString())
            lastname.setText(utils.getUser(appDb).lname.toString())
            age.setText(utils.getUser(appDb).age.toString())
            email.setText(utils.getUser(appDb).email)
            password.setText(utils.getUser(appDb).password)
            when (utils.getUser(appDb).gender) {
                "male" -> {
                    maleGender()
                }
                else -> {
                    femaleGender()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            flag = intent.getStringExtra("flag")!!
            try {
                when (flag) {
                    "logout" -> {
                        editprofile.visibility = View.GONE
                        logout.visibility = View.VISIBLE
                        logout.setOnClickListener {
                            editor.clear()
                            editor.apply()
                            appDb.userDao().deleteUser(
                                User(
                                    firstname.text.toString(),
                                    lastname.text.toString(),
                                    age.text.toString(),
                                    user_gender,
                                    email.text.toString(),
                                    password.text.toString()
                                )
                            )
                            val intent = Intent(this@EditProfileActivity, LoginActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    "!log" -> {
                        editprofile.text = "Register"
                        editprofile.setOnClickListener {
                            manageUser(
                                firstname.text.toString(),
                                lastname.text.toString(),
                                age.text.toString(),
                                user_gender,
                                email.text.toString(),
                                password.text.toString(), "insert"
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("error", "occurred")
                e.printStackTrace()
            }
        } catch (e: Exception) {
            editprofile.text = "Update Profile"
            e.printStackTrace()
        }

        try {
            when (editprofile.text) {
                "Update Profile" -> {
                    editprofile.setOnClickListener {
                        manageUser(
                            firstname.text.toString(),
                            lastname.text.toString(),
                            age.text.toString(),
                            user_gender,
                            email.text.toString(),
                            password.text.toString(), "update"
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun manageUser(
        s1: String,
        s2: String,
        s3: String,
        s4: String,
        s5: String,
        s6: String,
        s7: String
    ) {
        val appException = AppExecutors()
        when (s7) {
            "insert" -> {
                appException.diskIO().execute {
                    try {
                        val insert = appDb.userDao().insertUser(User(s1, s2, s3, s4, s5, s6))
                        Log.d("insert", insert.toString())
                        appException.mainThread().execute {
                            editor.putString("token", "logged").apply()
                            val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                        appException.mainThread().execute {
                            Toast.makeText(
                                applicationContext,
                                "Some Error Occured Please Try Again",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            e.printStackTrace()
                        }
                    }
                }
            }
            "update" -> {
                appException.diskIO().execute {
                    try {
                        val insert = appDb.userDao().updateUser(s1, s2, s3, s4, s5, s6)
                        Log.d("update", insert.toString())
                        appException.mainThread().execute {
                            Log.d("user", utils.getUser(appDb).email)
                            val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                        appException.mainThread().execute {
                            Toast.makeText(
                                applicationContext,
                                "Some Error Occured Please Try Again",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            startActivity(Intent(this, MainActivity::class.java))
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun maleGender() {
        male.background = getDrawable(R.drawable.background)
        male.setTextColor(resources.getColor(R.color.white))
        female.background = getDrawable(R.drawable.unselectedbackground)
        female.setTextColor(resources.getColor(R.color.colorAccent))
        user_gender = "male"
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun femaleGender() {
        female.background = getDrawable(R.drawable.background)
        female.setTextColor(resources.getColor(R.color.white))
        male.background = getDrawable(R.drawable.unselectedbackground)
        male.setTextColor(resources.getColor(R.color.colorAccent))
        user_gender = "female"
    }

    companion object {
        var user_gender: String = "male"
        var flag: String = ""
    }
}


