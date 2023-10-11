package com.example.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //region Init Views
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etUsername = findViewById<EditText>(R.id.etUserName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val txtStatusMessage = findViewById<TextView>(R.id.txtStatusMessage)
        //endregion

        val userList= ArrayList<UserData>()



        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()


            if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                txtStatusMessage.visibility = View.GONE


                userList.add(UserData(1, email, username, password))


                txtStatusMessage.text = "Added details of $username"
                txtStatusMessage.visibility = View.VISIBLE

                if (userList.isNotEmpty()) {
                    Log.d("LIST", "LIST: $userList")
                }
            } else {
                txtStatusMessage.text = "Please enter the details"
                txtStatusMessage.visibility = View.VISIBLE
            }


        }



    }
}