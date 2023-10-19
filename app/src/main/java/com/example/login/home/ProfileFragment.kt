package com.example.login.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.login.MainActivity
import com.example.login.R

class ProfileFragment : Fragment() {
    private lateinit var tvTitleName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvFullName: TextView

    private lateinit var layoutLoin: LinearLayout
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false).apply {
            tvTitleName = findViewById(R.id.tvTitlename)
            tvUsername = findViewById(R.id.tvUserName)
            tvEmail = findViewById(R.id.tvEmail)
            tvFullName = findViewById(R.id.tvFullName)
            layoutLoin = findViewById(R.id.layoutLogin)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val fullName =
            "${sharedPref.getString("firstName", "")} ${sharedPref.getString("lastName", "")}"
        tvTitleName.text = fullName
        tvUsername.text = sharedPref.getString("username", "")
        tvEmail.text = sharedPref.getString("email", "")
        tvFullName.text = fullName

        layoutLoin.setOnClickListener {
            logout()
        }

    }

    private fun logout() {
        sharedPref.edit().apply {
            clear()
        }.apply()
        startActivity(Intent(this.context, MainActivity::class.java)).also {
            requireActivity().finish()
        }
    }
}