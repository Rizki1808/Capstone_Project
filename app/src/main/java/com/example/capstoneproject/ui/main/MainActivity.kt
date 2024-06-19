package com.example.capstoneproject.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.data.tools.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityMainBinding
import com.example.capstoneproject.ui.login.signin.SignInActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSession().observe(this@MainActivity) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_feature,
                R.id.navigation_explore,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Handle bottom navigation item clicks
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home, null, NavOptions.Builder().setPopUpTo(R.id.navigation_home, true).build())
                    true
                }
                R.id.navigation_feature -> {
                    navController.navigate(R.id.navigation_feature, null, NavOptions.Builder().setPopUpTo(R.id.navigation_feature, true).build())
                    true
                }
                R.id.navigation_explore -> {
                    navController.navigate(R.id.navigation_explore, null, NavOptions.Builder().setPopUpTo(R.id.navigation_explore, true).build())
                    true
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile, null, NavOptions.Builder().setPopUpTo(R.id.navigation_profile, true).build())
                    true
                }
                else -> false
            }
        }
    }
}