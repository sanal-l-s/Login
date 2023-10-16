package com.example.login.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.login.R
import com.example.login.data.ApiService
import com.example.login.data.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val TAG = "TestLog"

    private val apiService: ApiService = RetrofitHelper.getInstance().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make the API request using a coroutine
        GlobalScope.launch(Dispatchers.Main) {

            val quote = apiService.getRandomQuote().body()?.first()
            Log.i(TAG, "onCreate: ${quote ?: "Failed to fetch quote"}")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}