@file:Suppress("UNUSED_VARIABLE")

package com.dev_app.ecommercesales

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.NavUtils
import androidx.core.content.ContextCompat
import com.dev_app.ecommercesales.models.Products
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_page.*
import kotlin.properties.Delegates


@Suppress("SpellCheckingInspection")
@SuppressLint("SetTextI18n")
class DetailPagActivity : AppCompatActivity() {

    private var priceText by Delegates.notNull<Int>()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

           // val view = inflate(this,R.layout.payment_custom_layout,null)

        var scrollView = findViewById<ScrollView>(R.id.scrollView)

        val plusbtn = findViewById<Button>(R.id.add)
        val minusbtn = findViewById<Button>(R.id.minus)

        var disclaimer = findViewById<TextView>(R.id.dump_text)
        var deliverable = findViewById<TextView>(R.id.card1_title)
        var parcelable = findViewById<TextView>(R.id.card2_title)
        val cartnoTxt = findViewById<TextView>(R.id.cartno_text)
        val footerCart =  findViewById<Button>(R.id.footer_cart)
        val price = findViewById<TextView>(R.id.price)
        val descriptionTitle = findViewById<TextView>(R.id.desc_title)

        var cardView1 = findViewById<CardView>(R.id.card1)
        val cardViewCall = findViewById<CardView>(R.id.card2)

        priceText = intent.getIntExtra("price", 1)

        val descriptionText = findViewById<TextView>(R.id.desc_text)
        descriptionTitle.text = intent.getStringExtra("name")
        descriptionText.text = intent.getStringExtra("details")

        val imagedesc = findViewById<ImageView>(R.id.imagedesc)
        Picasso.get().load(intent.getStringExtra("url"))
            .placeholder(R.drawable.trans_vada)
            .into(imagedesc)

        price.text = "₦$priceText only"


        footer_cart.setOnClickListener {
//            intent.putExtra("myprice",price.toString())
            intent.putExtra("mytitle", descriptionTitle.text)
           intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
               // mAlertDialog.dismiss()
            cardViewCall.setOnClickListener{
                onCall()
            }


        plusbtn.setOnClickListener {
            addInteger(cartnoTxt.text.toString())
        }

        minusbtn.setOnClickListener {
            minusInteger(cartnoTxt.text.toString())
        }
    }
    fun addInteger(addNo: String) {
        try {
            var i: Int = addNo.toInt()
            if (i >= 1) {
                i++
                val j: Int = i
                cartno_text.text = j.toString()
                val priceTxt = priceText.times(j)
                price.text = "₦$priceTxt only"
            }
        } catch (e: Exception) {
            Log.d("error", "occurred")
            e.printStackTrace()
        }
    }

    fun minusInteger(minusNo: String) {
        try {
            var i: Int = minusNo.toInt()
            if (i != 0 && i > 1) {
                i--
                val j: Int = i
                cartno_text.text = j.toString()
                val priceTxt = priceText.times(j)
                price.text = "₦$priceTxt only"
            }
        } catch (e: Exception) {
            Log.d("error", "occurred")
            e.printStackTrace()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //...Call to order function...
    private fun onCall() {
        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CALL_PHONE),
                234
            )
        } else {
            startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:07063551229")))
        }
    }
}