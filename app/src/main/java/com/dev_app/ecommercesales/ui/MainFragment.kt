@file:Suppress("UNUSED_VARIABLE", "MemberVisibilityCanBePrivate")

package com.dev_app.ecommercesales.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dev_app.ecommercesales.BuildConfig
import com.dev_app.ecommercesales.DetailPagActivity
import com.dev_app.ecommercesales.PaymentActivity
import com.dev_app.ecommercesales.R
import com.dev_app.ecommercesales.database.AppDb1
import com.dev_app.ecommercesales.models.Products
import com.dev_app.ecommercesales.viewModel.VModel
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.prod_ver_item.view.*
import java.lang.Exception

@Suppress("unused")
class MainFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var recyclerView1: RecyclerView
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var myPager: MyPager
    lateinit var sadapter1: CustomAdapter1
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var vModel: VModel
    lateinit var appDb1: AppDb1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        vModel = ViewModelProvider(this).get(VModel::class.java)
        MainFragment.context = requireContext()
        var scrollView: NestedScrollView = rootView.findViewById(R.id.home_body)
        recyclerView1 = rootView.findViewById(R.id.products_recycler_view)
        tabLayout = rootView.findViewById(R.id.tabDots)
        viewPager = rootView.findViewById(R.id.pager)
        tabLayout.setupWithViewPager(viewPager, false)
        myPager = MyPager(activity)
        viewPager.adapter = myPager

        recyclerView1.apply {
            val layoutManager1 = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerView1.layoutManager = layoutManager1

            vModel.getProducts(context).observe(viewLifecycleOwner, Observer { data ->
                run {
                    sadapter1 = CustomAdapter1(data as ArrayList<Products>)
                    recyclerView1.adapter = sadapter1
                    sadapter1.notifyDataSetChanged()
                }
            })
        }
    }

    class MyPager(private val context: Context?) : PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return `object` == view
        }

        override fun getCount(): Int {
            return 4
        }

        @SuppressLint("InflateParams")
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view =
                LayoutInflater.from(context).inflate(R.layout.pager_item, null)
            val imageView =
                view.findViewById<ImageView>(R.id.image)
            Picasso.get().load(getImageAt(position)).placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }

        private fun getImageAt(position: Int): String? {
            Log.d("myPager", "called")
            return when (position) {
                0 -> {
                    val path0 =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.banner)
                    path0.toString()
                }
                1 -> {
                    val path =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.nike_banner)
                    path.toString()
                }
                2 -> {
                    val path1 =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.balenciaga_banner)
                    path1.toString()
                }
                3 -> {
                    val path2 =
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.banner)
                    path2.toString()
                }
                else -> thumbnail
            }
        }
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

            holder.itemView.addtoCart.setOnClickListener {
                try {
                   addToCart(sList[position])
                   Toast.makeText(context, "Added To Cart!!", Toast.LENGTH_LONG).show()
               } catch (e: Exception){
                   Toast.makeText(context,"Already Added To Cart...",Toast.LENGTH_SHORT).show()
               }
            }

            holder.itemView.pro_item_card_body.setOnClickListener {
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
                val body = itemView.findViewById<CardView>(R.id.body)
                val name = itemView.findViewById<TextView>(R.id.name)
                val desc = itemView.findViewById<TextView>(R.id.description)
                val pricetxt = itemView.findViewById<TextView>(R.id.review)
                val cart = itemView.findViewById<ImageView>(R.id.addtoCart)
                val prod_img = itemView.findViewById<ImageView>(R.id.img)
                name.text = sdata.name
                desc.text = sdata.desc
                pricetxt.text = "₦${sdata.price} only"
                Picasso.get().load(sdata.image).placeholder(R.drawable.trans_vada).into(prod_img)
            }
        }
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
        var TAG = MainFragment::class.java.simpleName
        lateinit var context: Context
        lateinit var appDb1: AppDb1
        var thumbnail: String = "https://photos.google.com/?tab=iq&authuser=0&pageId=none"
        fun addToCart(sdata: Products) {
            appDb1 = Room.databaseBuilder(context, AppDb1::class.java, "Products")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()
            appDb1.productsDao().insertProduct(sdata)
        }
    }

    //...OnItem CLick

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

}