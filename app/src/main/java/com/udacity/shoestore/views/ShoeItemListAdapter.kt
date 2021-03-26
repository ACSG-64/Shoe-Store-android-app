package com.udacity.shoestore.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeCardBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.shoe_card.view.*

class ShoeItemListAdapter(val shoesItemsList: ArrayList<Shoe>) : RecyclerView.Adapter<ShoeItemListAdapter.ShoeViewHolder>(), CardClickListener{

    fun updateShoeList(newShoeList: List<Shoe>){
        shoesItemsList.clear()
        shoesItemsList.addAll(newShoeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ShoeCardBinding>(inflater, R.layout.shoe_card, parent, false)
        return ShoeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.view.shoe = shoesItemsList[position]
        holder.view.clickListener = this
    }

    override fun onCardClicked(card: View) {
        card.shoe_description?.visibility = View.VISIBLE
        card.expandDetails_button?.visibility = View.GONE
        //v.shoe_description?.visibility = View.VISIBLE
        Log.d("INTERFACE", "OK")
    }

    override fun getItemCount() = shoesItemsList.size

    class ShoeViewHolder(var view: ShoeCardBinding) : RecyclerView.ViewHolder(view.root)
}