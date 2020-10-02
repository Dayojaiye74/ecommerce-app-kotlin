package com.dev_app.ecommercesales.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.dev_app.ecommercesales.DetailPagActivity
import com.dev_app.ecommercesales.R
import com.dev_app.ecommercesales.database.SearchDb
import com.dev_app.ecommercesales.models.Products
import com.squareup.picasso.Picasso

class SearchFragment : Fragment() {

    private lateinit var rootView: View
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var searchDb: SearchDb
    lateinit var sadapter1: CustomAdapter1
    private var arrayList: ArrayList<Products> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_search, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        searchDb = Room
            .databaseBuilder(rootView.context, SearchDb::class.java, "Search")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        val editText = rootView.findViewById<EditText>(R.id.search)
        val button = rootView.findViewById<Button>(R.id.btnsearch)
        button.setOnClickListener {
            searchData(editText.text.toString())
        }
        recyclerView = rootView.findViewById(R.id.my_recycler_view)
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        sadapter1 = CustomAdapter1(arrayList)
        recyclerView.adapter = sadapter1
        sadapter1.notifyDataSetChanged()
    }

    class CustomAdapter1(private val sList: ArrayList<Products>) :
        RecyclerView.Adapter<CustomAdapter1.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.prod_ver_item, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
            holder.itemView.setOnClickListener {
                val intent = Intent(context, DetailPagActivity::class.java)
                intent.putExtra("name", sList[position].name)
                intent.putExtra("url", sList[position].image)
                intent.putExtra("details", sList[position].desc)
                intent.putExtra("price", sList[position].price)
                context.startActivity(intent)
            }
            holder.bindItems(sList[position])
        }

        override fun getItemCount(): Int {
            return sList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            @SuppressLint("SetTextI18n")
            fun bindItems(sdata: Products) {
               // itemView.findViewById<CardView>(R.id.body)
                val name = itemView.findViewById<TextView>(R.id.name)
                val desc = itemView.findViewById<TextView>(R.id.description)
                val pricetxt = itemView.findViewById<TextView>(R.id.review)
                val prod_img = itemView.findViewById<ImageView>(R.id.img)
                name.text = sdata.name
                desc.text = sdata.desc
                pricetxt.text = "₦${sdata.price} only"
                Picasso.get().load(sdata.image).placeholder(R.drawable.trans_vada).into(prod_img)
            }
        }
    }

    private fun searchData(string: String) {
        try {
            arrayList.clear()
            val list = searchDb.searcDao().findByName(string)
            arrayList.addAll(list)
            sadapter1.notifyDataSetChanged()
        } catch (e: Exception) {
            Log.d("error", "called::$e")
        }
    }
}