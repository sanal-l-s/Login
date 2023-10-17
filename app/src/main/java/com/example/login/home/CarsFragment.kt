package com.example.login.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.CarsApiHelper
import com.example.login.data.CarsApiService
import com.example.login.data.Credential
import com.example.login.data.LoginAPiService
import com.example.login.data.LoginApiHelper
import com.example.login.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CarsFragment : Fragment() {
    private lateinit var adapter: CarsAdapter
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

        rvCarList.layoutManager = LinearLayoutManager(this.context)
        adapter = CarsAdapter(mutableListOf())
        rvCarList.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            val carsAPiService: CarsApiService = CarsApiHelper.getInstance().create(
                CarsApiService::class.java
            )

            val carsResponse = carsAPiService.getCars()
            val carList = carsResponse.body()?.results
            Log.i("TAG", "onViewCreated: ${carList}")
            carList.let {
                adapter.updateData(carList as List<Result>)
            }
           /* val ctx = this@CarsFragment
            ctx.runOnUiThread {

            }

            carsResponse.body()?.let { adapter.updateData(it.results as List<Result>) }*/

        }



    }
}