package com.example.login.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.CarsApiHelper
import com.example.login.data.CarsApiService
import com.example.login.data.Result
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CarsFragment : Fragment() {
    private lateinit var adapter: CarsAdapter
    private lateinit var rvCarList: RecyclerView
    private lateinit var pbCars: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_cars, container, false).apply {
            rvCarList = findViewById(R.id.rvCars)
            pbCars = findViewById(R.id.pbCars)
        }.also {
            return it
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvCarList.layoutManager = LinearLayoutManager(this.context)
        adapter = CarsAdapter(mutableListOf()) { car ->

            Toast.makeText(this.context, "Car ${car.mfrName}", Toast.LENGTH_SHORT).show()
        }
        rvCarList.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            val carsAPiService: CarsApiService = CarsApiHelper.getInstance().create(
                CarsApiService::class.java
            )
            rvCarList.hide()
            pbCars.show()

            val carsResponse = carsAPiService.getCars()

            adapter.updateData(carsResponse.body()?.results as List<Result>)

            pbCars.hide()
            rvCarList.show()


        }

    }

    private fun View.show() {
        visibility = View.VISIBLE
    }

    private fun View.hide() {
        visibility = View.GONE
    }
}