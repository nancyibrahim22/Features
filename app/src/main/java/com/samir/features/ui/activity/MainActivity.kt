package com.samir.features.ui.activity

 import android.os.Bundle
 import android.view.KeyEvent
 import android.view.View
 import androidx.appcompat.app.AppCompatActivity
 import androidx.fragment.app.Fragment
 import androidx.fragment.app.FragmentManager
 import androidx.navigation.NavController
 import androidx.navigation.fragment.NavHostFragment
 import androidx.navigation.ui.NavigationUI
 import com.google.android.material.bottomnavigation.BottomNavigationView
 import com.samir.features.R
 import com.samir.features.databinding.ActivityMainBinding
 import com.samir.features.ui.fragment.back.BackFragment


class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding?.getRoot())
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.othersFragment -> showBottomNav()
                R.id.serviceListFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding?.bottomNavigationView?.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding?.bottomNavigationView?.visibility = View.GONE
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            val id = navController.currentDestination?.id
            if (id ==  R.id.backFragment) {
                //this means that if the user pressed the back button then it will (not) get back to the previous fragment
                true
            }
            else
            {
                //this means that if the user pressed the back button then it will get back to the previous fragment
                super.dispatchKeyEvent(event)
            }
        } else super.dispatchKeyEvent(event)



    }
}