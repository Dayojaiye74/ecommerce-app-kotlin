package com.dev_app.ecommercesales.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dev_app.ecommercesales.R
import com.dev_app.ecommercesales.adapter.ProductsAdapter
import com.dev_app.ecommercesales.adapter.ShoesAdapter
import com.dev_app.ecommercesales.models.Product
import com.dev_app.ecommercesales.models.ShoeFragmentViewModel
import com.dev_app.ecommercesales.models.ShoesProduct
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.progressBar
import kotlinx.android.synthetic.main.fragment_shoe.*


class ShoeFragment : Fragment() {

    lateinit var viewModel: ShoeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ShoeFragmentViewModel::class.java)

        viewModel.shoeProducts.observe(requireActivity(), Observer {
            loadRecyclerView(it)
        })

        viewModel.setup()

        //... search view
       /* shoeSearchButton.setOnClickListener {
            viewModel.searchShoes(searchText.text.toString())
        }

        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchShoes(searchText.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
            */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_shoe, container, false)

        return root
    }

    //...RecyclerView function to load products
    private fun loadRecyclerView(shoeProducts: List<ShoesProduct>) {
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)

            adapter = ShoesAdapter(shoeProducts) { extraTitle, extraImageUrl, photoView ->
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