package com.odogwudev.benshiposts.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.odogwudev.benshiposts.R
import com.odogwudev.benshiposts.databinding.ActivityMainBinding
import com.odogwudev.benshiposts.ui.posts.PostFragment
import com.odogwudev.benshiposts.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()
        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    loadFragment(PostFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.page_2 -> {
                    loadFragment(SettingsFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}