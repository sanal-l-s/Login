package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.home.HomeActivity

class MainActivity : AppCompatActivity() {
    private val btnLogin: Button by lazy { findViewById(R.id.btnLogin) }
    private val etUserName: EditText by lazy { findViewById(R.id.etUserName) }
    private val etPassword: EditText by lazy { findViewById(R.id.etPassword) }
    private val txtErrorMessage: TextView by lazy { findViewById(R.id.txtErrorMessage) }
    private val txtSignup: TextView by lazy { findViewById(R.id.txtSignup) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupClickListeners()


    }

    private fun setupClickListeners() {
        btnLogin.setOnClickListener {
            hideKeyboard()

            val username = etUserName.text.toString()
            val password = etPassword.text.toString()

            if (isCredentialsValid(username, password)) {
                txtErrorMessage.visibility = View.INVISIBLE
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, ProfileActivity::class.java))
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                txtErrorMessage.showError("Invalid username or password")
            }
        }

        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun isCredentialsValid(username: String, password: String): Boolean {
        return true //for skipping validation
        return username == "admin" && password == "admin@123"
    }

    private fun TextView.showError(message: String) {
        text = message
        visibility = View.VISIBLE
    }

    private fun hideKeyboard() {
        val view: View? = this.currentFocus
        view?.let { hideSoftInputFromWindow(it.windowToken) }
    }

    private fun hideSoftInputFromWindow(windowToken: IBinder) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}