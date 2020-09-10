package com.dev_app.ecommercesales.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_app.ecommercesales.`interface`.ProductsRepository
import com.dev_app.ecommercesales.`interface`.shoe.ShoesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoeFragmentViewModel: ViewModel() {


    val shoeProducts = MutableLiveData<List<ShoesProduct>>()

    fun setup() {
        viewModelScope.launch(Dispatchers.Default) {
            shoeProducts.postValue(ShoesRepository().fetchAllShoesRetrofit())
        }
    }
    fun searchShoes(term: String) {
        viewModelScope.launch(Dispatchers.Default) {
            shoeProducts.postValue(ShoesRepository().searchForShoesProducts(term))
        }
    }



}