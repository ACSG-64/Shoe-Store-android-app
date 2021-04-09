package com.udacity.shoestore.views

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.BR
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeCardBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModels.ShoeListViewModel
import kotlinx.android.synthetic.main.fragment_shoe_listing.*
import kotlinx.android.synthetic.main.shoe_card.view.*


class ShoeListingFragment : Fragment(), CardClickListener {

    private lateinit var viewModel : ShoeListViewModel

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

        viewModel.shoeItems.observe(viewLifecycleOwner, Observer<List<Shoe>>{ shoes ->
            for (shoe in shoes){
                val shoeCard = View.inflate(context, R.layout.shoe_card, null)
                DataBindingUtil.bind<ShoeCardBinding>(shoeCard)
                shoe_list.addView(shoeCard)
                val binding = DataBindingUtil.getBinding<ShoeCardBinding>(shoeCard)
                binding?.setVariable(BR.shoe, shoe)
                binding?.setVariable(BR.clickListener, this)
                binding?.executePendingBindings()
            }
        })

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

    override fun onCardClicked(card: View) {
        card.shoe_description?.visibility = View.VISIBLE
        card.expandDetails_button?.visibility = View.GONE
        Log.d("INTERFACE", "OK")
    }

}