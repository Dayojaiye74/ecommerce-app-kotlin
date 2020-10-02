package com.dev_app.ecommercesales

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlin.properties.Delegates

class PaymentActivity : AppCompatActivity() {

    private var myText by Delegates.notNull<Int>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val priceText = findViewById<TextView>(R.id.paymentText)
        val paymentTitle = findViewById<TextView>(R.id.title_check_out)

        myText = intent.getIntExtra("myprice", 0)
        priceText.text = myText.toString()

        paymentTitle.text = intent.getStringExtra("mytitle")

//priceText.text = intent.getStringExtra("myprice")
        //priceText.text = myText.toString()
       // priceText.text = "₦$priceText"

    }
}


