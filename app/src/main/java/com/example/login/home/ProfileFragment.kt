package com.example.login.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.login.MainActivity
import com.example.login.R

class ProfileFragment : Fragment() {
    private lateinit var tvUsername: TextView
    private lateinit var btnLogout: Button
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false).apply {
            tvUsername = findViewById(R.id.txtProfileName)
            btnLogout = findViewById(R.id.btnLogout)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        tvUsername.text = sharedPref.getString("username", "")
        btnLogout.setOnClickListener {
            logout()
        }

    }

    private fun logout() {
        sharedPref.edit().apply {
            clear()
        }.apply()
        startActivity(Intent(this.context,MainActivity::class.java)).also {
            requireActivity().finish()
        }
    }
}