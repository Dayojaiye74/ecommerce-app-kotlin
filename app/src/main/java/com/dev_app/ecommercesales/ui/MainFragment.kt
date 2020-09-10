package com.dev_app.ecommercesales.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dev_app.ecommercesales.R
import com.dev_app.ecommercesales.adapter.CategoriesAdapter
import com.dev_app.ecommercesales.adapter.ProductsAdapter
import com.dev_app.ecommercesales.models.MainFragmentViewModel
import com.dev_app.ecommercesales.models.Product
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {

     lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        //..Category recyclerview for the products
        val categories = listOf(
            "Shoes",
            "Socks",
            "Suits",
            "Skirts",
            "Dresses",
            "Jeans",
            "Pants",
            "Jackets",
            "Sandals"
        )

        root.categoriesRecyclerView.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                activity,
                androidx.recyclerview.widget.RecyclerView.HORIZONTAL,
                false
            )

            adapter = CategoriesAdapter(categories)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        //viewModel = ViewModelProviders.of(requireActivity()).get(MainFragmentViewModel::class.java)

        viewModel.products.observe(requireActivity(), Observer {
            loadRecyclerView(it)
        })

        viewModel.setup()

        //... search view
        searchButton.setOnClickListener {
            viewModel.search(searchTerm.text.toString())
        }

        searchTerm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.search(searchTerm.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    //...RecyclerView function to load products
    private fun loadRecyclerView(products: List<Product>) {
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)

            adapter = ProductsAdapter(products) { extraTitle, extraImageUrl, photoView ->
//                val intent = Intent(activity, ProductDetails::class.java)
//                intent.putExtra("title", extraTitle)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity as AppCompatActivity,
                    photoView,
                    "photoToAnimate"
                )
                //startActivity(intent, options.toBundle())
            }

        }
        progressBar.visibility = View.GONE
    }


}