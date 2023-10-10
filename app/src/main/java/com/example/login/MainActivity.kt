package com.example.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
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
        val txtProfileName: TextView = findViewById(R.id.txtProfileName)
        val llProfileScreen: LinearLayout = findViewById(R.id.llProfileScreen)
        val llLoginScreen: LinearLayout = findViewById(R.id.llLoginScreen)
        val imgClose: ImageView = findViewById(R.id.imgClose)


        btnLogin.setOnClickListener {

            hideKeyboard()

            val username = etUserName.text.toString()
            val password = etPassword.text.toString()

            if (username == "admin" && password == "admin@123") {
                txtErrorMessage.visibility = View.INVISIBLE
                txtProfileName.text = username
                llLoginScreen.visibility = View.GONE
                llProfileScreen.visibility = View.VISIBLE
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                txtErrorMessage.text = buildString {
                    append("Invalid username or password")
                }
                txtErrorMessage.visibility = View.VISIBLE
                llProfileScreen.visibility = View.GONE
                llLoginScreen.visibility = View.VISIBLE
//                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
        imgClose.setOnClickListener {
            llProfileScreen.visibility = View.GONE
            llLoginScreen.visibility = View.VISIBLE
        }

    }

    private fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}