package com.udacity.shoestore.views

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModels.ShoeListViewModel
import kotlinx.android.synthetic.main.fragment_shoe_listing.*


class ShoeListingFragment : Fragment() {

    private lateinit var viewModel : ShoeListViewModel
    private val shoeItemListAdapter = ShoeItemListAdapter(arrayListOf())
    private val shoeItemObserver = Observer<List<Shoe>>{ shoes ->
        shoeItemListAdapter.updateShoeList(shoes)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_shoe_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false) // Hide the back button

        viewModel= ViewModelProvider(activity!!).get(ShoeListViewModel::class.java)
        viewModel.shoeItems.observe(viewLifecycleOwner, shoeItemObserver)

        shoeList_recyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = shoeItemListAdapter
        }

        addShoe_button.setOnClickListener {
            view.findNavController().navigate(R.id.action_shoeListingFragment_to_shoeDetailFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.logoutmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}