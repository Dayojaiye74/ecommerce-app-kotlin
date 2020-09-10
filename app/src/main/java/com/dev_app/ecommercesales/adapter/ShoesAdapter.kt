package com.dev_app.ecommercesales.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev_app.ecommercesales.R
import com.dev_app.ecommercesales.models.ShoesProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ShoesAdapter(
    private val products: List<ShoesProduct>,
    private val onClickShoesProduct: (title: String, photoUrl: String, photoView: View) -> Unit
) : RecyclerView.Adapter<ShoesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ShoesAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString()

        if (product.isOnSale) {
            holder.saleImageView.visibility = View.VISIBLE
        } else {
            holder.saleImageView.visibility = View.GONE
        }

        holder.image.setOnClickListener {
            onClickShoesProduct.invoke(product.title, product.photoUrl, holder.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoe_product_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = products.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val saleImageView: ImageView = itemView.saleImageView
    }
}