package com.example.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val etUserName: EditText = findViewById(R.id.etUserName)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val txtErrorMessage: TextView = findViewById(R.id.txtErrorMessage)

        btnLogin.setOnClickListener {
            val username = etUserName.text.toString()
            val password = etPassword.text.toString()

            if (username == "admin" && password == "admin@123") {
                txtErrorMessage.visibility = View.INVISIBLE
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                txtErrorMessage.text = buildString {
                    append("Invalid username or password")
                }
                txtErrorMessage.visibility = View.VISIBLE
//                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}