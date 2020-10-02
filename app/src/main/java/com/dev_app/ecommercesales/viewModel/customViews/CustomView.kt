package com.dev_app.ecommercesales.viewModel.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.dev_app.ecommercesales.R
import kotlinx.android.synthetic.main.activity_detail_page.view.*

class CustomView (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
     var titleCheckOut: TextView

    init {
        View.inflate(context, R.layout.payment_custom_layout,this)
        titleCheckOut = findViewById(R.id.title_check_out)
        val footerCart =  findViewById<Button>(R.id.footer_cart)

        footerCart.setOnClickListener {
            desc_title.text = titleCheckOut.text
            myDialog()
        }

//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
//        imageView.setImageDrawable(attributes.getDrawable(R.styleable.BenefitView_image))
//        textView.text = attributes.getString(R.styleable.BenefitView_text)
    }


    fun myDialog(){
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.payment_custom_layout,null)
        val mBuilder = AlertDialog.Builder(context).setView(mDialogView).setTitle("Payment Checkout")
        //..show Dialog...
        val mAlertDialog = mBuilder.show()

        mDialogView.setOnClickListener(){
            mAlertDialog.dismiss()
        }
    }

}

