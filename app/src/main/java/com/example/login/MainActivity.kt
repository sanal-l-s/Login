package com.example.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login.data.Credential
import com.example.login.data.LoginAPiService
import com.example.login.data.LoginApiHelper
import com.example.login.data.UserData
import com.example.login.home.HomeActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var errorMessage: String = ""

    // Lazy initialization for UI elements
    private val btnLogin: Button by lazy { findViewById(R.id.btnLogin) }
    private val etUserName: EditText by lazy { findViewById(R.id.etUserName) }
    private val etPassword: EditText by lazy { findViewById(R.id.etPassword) }
    private val txtErrorMessage: TextView by lazy { findViewById(R.id.txtErrorMessage) }
    private val pbLogin: ProgressBar by lazy { findViewById(R.id.pbLogin) }
    private val txtSignup: TextView by lazy { findViewById(R.id.txtSignup) }
    private val sharedPref: SharedPreferences by lazy {
        getSharedPreferences(
            "MyAppPrefs",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check if user data is already stored
        if (sharedPref.contains("id")) {
            // If yes, go to the home page
            startActivity(Intent(this, HomeActivity::class.java)).also {
                finish()
            }
        } else {
            // Otherwise, set up click listeners
            setupClickListeners()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun setupClickListeners() {
        // Set up a click listener for the login button
        btnLogin.setOnClickListener {
            // Hide the keyboard
            hideKeyboard()

            // Get username and password from input fields
            val username = etUserName.text.toString()
            val password = etPassword.text.toString()

            // Launch a coroutine for the login process
            GlobalScope.launch(Dispatchers.Main) {
                // Hide any error message
                txtErrorMessage.hide()
                // Show a loading indicator
                pbLogin.show()

                // Check if the credentials are valid
                isCredentialsValid(username, password) { isValid ->
                    pbLogin.hide()
                    val ctx = this@MainActivity
                    if (isValid) {
                        // If login is successful, display a toast message
                        txtErrorMessage.hide()
                        Toast.makeText(ctx, "Login Successful", Toast.LENGTH_SHORT).show()
                        // Go to the home page
                        startActivity(Intent(ctx, HomeActivity::class.java)).also {
                            finish()
                        }
                    } else {
                        // If login fails, show an error message
                        txtErrorMessage.showError(errorMessage)
                    }
                }
            }
        }

        // Set up a click listener for the signup text
        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    // Function to check if credentials are valid
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
        errorMessage = userResponse.body()?.message ?: "Invalid Username or password"

        val userData = userResponse.body()

        if (userResponse.isSuccessful) {
            // Store user data if the API call was successful
            storeUserData(userData)
        }

        // Check if the API call was successful and return the result via the callback
        callback(userResponse.isSuccessful)
    }

    // Function to store user data in SharedPreferences
    private fun storeUserData(userData: UserData?) {
        sharedPref.edit().apply {
            putInt("id", userData?.id!!)
            putString("token", userData.token)
            putString("email", userData.email)
            putString("username", userData.username)
            putString("firstName", userData.firstName)
            putString("lastName", userData.lastName)
            putString("gender", userData.gender)
            putString("image", userData.image)
        }.apply()
    }

    private fun TextView.showError(message: String) {
        text = message
        visibility = View.VISIBLE
    }

    private fun TextView.hide() {
        visibility = View.INVISIBLE
    }

    private fun View.hide() {
        visibility = View.INVISIBLE
    }

    private fun View.show() {
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