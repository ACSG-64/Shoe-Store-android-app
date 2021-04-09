package com.udacity.shoestore.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    val shoeItemsList = arrayListOf<Shoe>()
    val shoeItems by lazy { MutableLiveData<MutableList<Shoe>>() }

    fun addShoe(shoeItem: Shoe){
        shoeItemsList.add(shoeItem)
        shoeItems.setValue(shoeItemsList)
    }
}