package com.dev_app.ecommercesales.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev_app.ecommercesales.R
import com.squareup.picasso.Picasso

/*
class ProductAdapter (private var items:List<Product>, private val context: Context):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.product_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Picasso.get().load(item.image).into(holder.imageView)
        holder.titleView.text = item.productName
        holder.priceView.text = item.price.toString()

    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgUrl)
        val titleView: TextView = view.findViewById(R.id.title)
        val priceView: TextView = view.findViewById(R.id.price)
    }
} */