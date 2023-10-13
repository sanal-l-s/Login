package com.example.login

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private val btnFrag1: Button by lazy { findViewById<Button>(R.id.btnFrag1) }
    private val btnFrag2: Button by lazy { findViewById<Button>(R.id.btnFrag2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnFrag1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, FirstFragment())
                commit()
            }
        }
        btnFrag2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, SecondFragment())
                commit()
            }
        }
    }
}