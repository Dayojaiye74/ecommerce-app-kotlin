package com.dev_app.ecommercesales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev_app.ecommercesales.databinding.FragmentShoesBinding
import kotlinx.android.synthetic.main.fragment_shoes.*


class shoesFragment : Fragment() {

    private lateinit var viewModel: ShoesVIewModel
    private lateinit var binding: FragmentShoesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoes, container,false

        )

        retainInstance = true

        viewModel =ViewModelProvider(this).get(ShoesVIewModel::class.java)
        binding.shoesViewModel = viewModel

        binding.setLifecycleOwner(this)

        R.id.title
        R.id.profile_image
        R.id.price




        //.....

        recyclerview_shoes.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
        }


        return binding.root
    }


}
