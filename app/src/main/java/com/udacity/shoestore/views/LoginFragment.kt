package com.udacity.shoestore.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var emailField: EditText
    lateinit var passwordField: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.buttonLogin.setOnClickListener { view: View ->
            // Check if both edit text views are filled
            if ( fillFields(binding.editTextEmailAddress, binding.editTextPassword) ){
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_welcomeFragment)

            }
            else {
                Toast.makeText(activity, "Please, fill the fields correctly.", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonRegister.setOnClickListener { view: View ->
            // Check if both edit text views are filled
            if ( fillFields(binding.editTextEmailAddress, binding.editTextPassword) ){
                val navController = Navigation.findNavController(view)
                navController.popBackStack(R.id.welcomeFragment, true)
                navController.navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
            else {
                Toast.makeText(activity, "Please, fill the fields correctly.", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun fillFields(emailField: EditText, passwordField : EditText): Boolean {
        if ( (emailField.text.toString().trim().isNotEmpty() ||
                    emailField.text.toString().trim().isNotBlank())
            && (passwordField.text.toString().trim().isNotEmpty() ||
                    passwordField.text.toString().trim().isNotBlank()) ) {
            return true
        }
        return false
    }

}