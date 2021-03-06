package com.jake.bucketplace.snsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.jake.bucketplace.snsapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as SnsApplication).appComponent.inject(this)
        this._binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

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
        super.onDestroy()
        _binding = null
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