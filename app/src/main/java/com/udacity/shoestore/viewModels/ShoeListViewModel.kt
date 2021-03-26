package com.udacity.shoestore.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    val shoeItemsList = arrayListOf<Shoe>()
    val shoeItems by lazy { MutableLiveData<MutableList<Shoe>>() }

    fun addShoe(name: String, size: Double, company: String, description: String, image: Int){
        val shoe_item = Shoe(name, size, company, description, image)
        shoeItemsList.add(shoe_item)
        shoeItems.setValue(shoeItemsList)
    }
}