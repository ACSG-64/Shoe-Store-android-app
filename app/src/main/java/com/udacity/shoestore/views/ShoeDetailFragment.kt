package com.udacity.shoestore.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewModels.ShoeListViewModel
import kotlinx.android.synthetic.main.fragment_shoe_detail.*

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel : ShoeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(activity!!).get(ShoeListViewModel::class.java)

        addRecord_button.setOnClickListener {
            val name = shoeName_editText.text.toString().trim()
            val size = shoeSize_editText.text.toString().trim()
            val company = shoeCompany_editText.text.toString().trim()
            val description = shoeDescription_editText.text.toString().trim()
            val image = arrayOf<Int>(R.drawable.shoe_blue, R.drawable.shoe_red, R.drawable.shoe_brown).random() // Select the image of the shoe randomly

            if( fillFields(name, company, size) ){
                viewModel.addShoe(name, size.toDouble(), company, description, image)
                view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListingFragment)
            } else {
                Toast.makeText(activity, "Fill the fields with valid data!", Toast.LENGTH_LONG).show()
            }
        }

        cancelRecord_button.setOnClickListener {
            view.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListingFragment)
        }
    }

    private fun fillFields(nameField: String, companyField : String, sizeField : String): Boolean {
        if ( (nameField.isNotEmpty() || nameField.isNotBlank())
            && (companyField.isNotEmpty() || companyField.isNotBlank())
             && (sizeField.isNotEmpty() || sizeField.isNotBlank())) {
            return true
        }
        return false
    }
}