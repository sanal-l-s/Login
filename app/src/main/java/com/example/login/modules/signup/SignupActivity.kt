package com.example.login.modules.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R

class SignupActivity : AppCompatActivity() {

    private val etEmail by lazy { findViewById<EditText>(R.id.etEmail) }
    private val etUsername by lazy { findViewById<EditText>(R.id.etUserName) }
    private val etPassword by lazy { findViewById<EditText>(R.id.etPassword) }
    private val btnRegister by lazy { findViewById<Button>(R.id.btnRegister) }
    private val txtStatusMessage by lazy { findViewById<TextView>(R.id.txtStatusMessage) }
    private val userList = ArrayList<UserData>()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (areFieldsNotEmpty(email, username, password)) {
                txtStatusMessage.hide()

                userList.add(UserData(++id, email, username, password))

                txtStatusMessage.showWithMessage(getString(R.string.added_details_of, username))

                if (userList.isNotEmpty()) {
                    Log.d("LIST", "LIST: $userList")
                }
            } else {
                txtStatusMessage.showWithMessage(getString(R.string.please_enter_the_details))
            }
        }


    }

    private fun areFieldsNotEmpty(email: String, username: String, password: String): Boolean {
        return email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()
    }

    private fun TextView.showWithMessage(message: String) {
        text = message
        visibility = View.VISIBLE
    }

    private fun View.hide() {
        visibility = View.GONE
    }
}