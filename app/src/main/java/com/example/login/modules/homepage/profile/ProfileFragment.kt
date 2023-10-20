package com.example.login.modules.homepage.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.login.R
import com.example.login.modules.homepage.movie.MovieActivity
import com.example.login.modules.login.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {
    private lateinit var tvTitleName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvFullName: TextView
    private lateinit var imgProfile: ImageView
    private lateinit var layoutLogin: LinearLayout
    private lateinit var layoutMovieList: LinearLayout
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
            layoutLogin = findViewById(R.id.layoutLogin)
            layoutMovieList = findViewById(R.id.layoutMoviesLink)
            imgProfile = findViewById(R.id.ivProfileAvatar)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        //Displaying user details
        val fullName =
            "${sharedPref.getString("firstName", "")} ${sharedPref.getString("lastName", "")}"
        tvTitleName.text = fullName
        tvUsername.text = sharedPref.getString("username", "")
        tvEmail.text = sharedPref.getString("email", "")
        tvFullName.text = fullName

        //Loading image from url
        Glide.with(this)
            .load(sharedPref.getString("image", ""))
            .centerCrop()
            .placeholder(R.drawable.avatar_placeholder)
            .into(imgProfile)

        //handle logout action
        layoutLogin.setOnClickListener {
            //show alert for confirmation
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout") { dialog, which ->
                    // Handle logout logic here
                    logout()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    // Dismiss the dialog if "Cancel" is clicked.
                    dialog.dismiss()
                }
                .show()
        }
        layoutMovieList.setOnClickListener {
            startActivity(Intent(context, MovieActivity::class.java))
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