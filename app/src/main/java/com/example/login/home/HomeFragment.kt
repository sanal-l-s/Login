package com.example.login.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.login.R
import com.example.login.data.ApiService
import com.example.login.data.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var txtQuote: TextView
    private lateinit var pbHome: ProgressBar
    private lateinit var layoutHome: LinearLayout

    private val apiService: ApiService = RetrofitHelper.getInstance().create(ApiService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchData()

    }

    private fun fetchData() {
        // Make the API request using a coroutine
        GlobalScope.launch(Dispatchers.Main) {

            layoutHome.gone()
            pbHome.visible()

            val quote = apiService.getRandomQuote().body()?.first()

            "\"${ quote ?: "Failed to fetch quote" }\"".also { txtQuote.text = it }
            pbHome.gone()
            layoutHome.visible()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        txtQuote = rootView.findViewById(R.id.txtQuote)
        layoutHome = rootView.findViewById(R.id.layoutHome)
        pbHome = rootView.findViewById(R.id.pbHome)
        return rootView
    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.gone() {
        visibility = View.GONE
    }

}