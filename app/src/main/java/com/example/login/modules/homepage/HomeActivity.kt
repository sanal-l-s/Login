package com.example.login.modules.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.modules.homepage.cars.CarsFragment
import com.example.login.modules.homepage.dashboard.DashBoardFragment
import com.example.login.modules.homepage.home.HomeFragment
import com.example.login.modules.homepage.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val homeBottomNav: BottomNavigationView by lazy { findViewById(R.id.navHomePage) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeBottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navHome -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.containerHomeFragment, HomeFragment())
                        commit()
                    }
                    true
                }

                R.id.navDashboard -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.containerHomeFragment, DashBoardFragment())
                        commit()
                    }
                    true
                }

                R.id.navCars -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.containerHomeFragment, CarsFragment())
                        commit()
                    }
                    true
                }

                R.id.navProfile -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.containerHomeFragment, ProfileFragment())
                        commit()
                    }
                    true
                }

                else -> false
            }
        }

    }

    override fun onResume() {
        super.onResume()
        loadHomeFragment()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.containerHomeFragment)

        if (currentFragment is DashBoardFragment || currentFragment is CarsFragment || currentFragment is ProfileFragment) {
            loadHomeFragment()
        } else {
            super.onBackPressed() // Perform the default back button behavior
        }
    }


    private fun loadHomeFragment() {
        homeBottomNav.selectedItemId = R.id.navHome
    }
}