package com.example.login.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.Result

class CarsFragment : Fragment() {
    private lateinit var rvCarList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_cars, container, false).apply {
            rvCarList = findViewById(R.id.rvCars)
        }.also {
            return it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this creates a vertical layout Manager
        rvCarList.layoutManager = LinearLayoutManager(this.context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Result>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(Result(mfrCommonName = "Name", country = "Country"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CarsAdapter(data)

        // Setting the Adapter with the recyclerview
        rvCarList.adapter = adapter
    }
}