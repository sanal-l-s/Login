package com.example.login

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin :Button = findViewById(R.id.btnLogin)
        val etUserName :EditText = findViewById(R.id.etUserName)
        val etPassword :EditText = findViewById(R.id.etPassword)

        btnLogin.setOnClickListener {
            val username = etUserName.text.toString()
            val password = etPassword.text.toString()

            if (username == "admin" && password == "admin@123") {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}