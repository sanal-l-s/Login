package com.example.login.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val homeBottomNav: BottomNavigationView by lazy { findViewById<BottomNavigationView>(R.id.navHomePage) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerHomeFragment, HomeFragment())
            commit()
        }

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
}