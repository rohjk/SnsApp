package com.jake.bucketplace.snsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.jake.bucketplace.snsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        this.navController = navHostFragment.navController

        binding.apply {
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity
            this@MainActivity.toolbar = mainToolbar
        }

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
        val actionBar = supportActionBar
        actionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun signUp() {
        this.navController.navigate(NavHomeDirections.actionGlobalSignUpFragment())
    }

    fun signIn() {

    }

    fun singOut() {

    }
}