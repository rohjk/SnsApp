package com.jake.bucketplace.snsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.jake.bucketplace.snsapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as SnsApplication).appComponent.inject(this)
        this.binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        this.navController = navHostFragment.navController

        binding.apply {
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity
            viewModel = this@MainActivity.viewModel
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

    override fun onDestroy() {
        binding?.unbind()
        super.onDestroy()
    }

    fun signUp() {
        this.navController.navigate(NavHomeDirections.actionGlobalSignUpFragment())
    }

    fun signIn() {
        this.navController.navigate(NavHomeDirections.actionGlobalSignInFragment())
    }

    fun signOut() {
        viewModel.signOut()
    }
}