package com.example.login.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.login.R
import com.example.login.data.Result

class CarDetailsFragment(val car: Result) : Fragment() {
    private lateinit var txtCarName: TextView
    private lateinit var txtCarCountry: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_details, container, false).apply {
            txtCarName = findViewById(R.id.txtCarName)
            txtCarCountry = findViewById(R.id.txtCarCountry)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtCarName.text = car.vehicleTypes.toString()
        txtCarCountry.text = car.country
    }

}