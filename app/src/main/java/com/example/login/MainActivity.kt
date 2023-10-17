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
import com.example.login.data.Credential
import com.example.login.data.LoginAPiService
import com.example.login.data.LoginApiHelper
import com.example.login.home.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var errorMessage: String = ""
    private val btnLogin: Button by lazy { findViewById(R.id.btnLogin) }
    private val etUserName: EditText by lazy { findViewById(R.id.etUserName) }
    private val etPassword: EditText by lazy { findViewById(R.id.etPassword) }
    private val txtErrorMessage: TextView by lazy { findViewById(R.id.txtErrorMessage) }
    private val txtSignup: TextView by lazy { findViewById(R.id.txtSignup) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //hardcoding credentials TODO: remove after use
        etUserName.setText("kdulyt")
        etPassword.setText("5t6q4KC7O")


        setupClickListeners()


    }

    private fun setupClickListeners() {
        btnLogin.setOnClickListener {
            hideKeyboard()

            val username = etUserName.text.toString()
            val password = etPassword.text.toString()

//            login(username, password)

            GlobalScope.launch {
                isCredentialsValid(username, password) { isValid ->
                    val ctx = this@MainActivity
                    ctx.runOnUiThread {
                        if (isValid) {
                            txtErrorMessage.hide()
                            Toast.makeText(ctx, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(ctx, HomeActivity::class.java))
                        } else {
                            txtErrorMessage.showError(errorMessage)
                        }
                    }
                }
            }

        }

        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }


    private suspend fun isCredentialsValid(
        username: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        val loginAPiService: LoginAPiService = LoginApiHelper.getInstance().create(
            LoginAPiService::class.java
        )

        val credential = Credential(username, password)
        val userResponse = loginAPiService.login(credential)
        errorMessage = userResponse.body()?.message ?: "Invalid"

        // Check if the API call was successful and return the result via the callback
        callback(userResponse.isSuccessful)
    }

    private fun TextView.showError(message: String) {
        text = message
        visibility = View.VISIBLE
    }

    private fun TextView.hide() {
        visibility = View.INVISIBLE
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