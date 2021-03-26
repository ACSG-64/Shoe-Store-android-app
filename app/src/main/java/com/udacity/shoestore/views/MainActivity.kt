package com.udacity.shoestore.views

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // val navController = this.findNavController(R.id.myNavHostFragment)
        // if(navController.currentDestination?.id == R.id.shoeListingFragment) {actionBar?.setDisplayHomeAsUpEnabled(false)}
        // return navController.navigateUp()

        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}