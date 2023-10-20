package com.example.login.modules.homepage.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.databinding.FragmentCarsBinding
import com.example.login.model.cars.Result
import com.example.login.modules.homepage.cars.cardetails.CarDetailsFragment
import com.example.login.modules.homepage.cars.viewmodel.CarsViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

class CarsFragment : Fragment() {
    private lateinit var binding: FragmentCarsBinding
    private lateinit var carsAdapter: CarsAdapter
    private lateinit var viewModel: CarsViewModel


//    private lateinit var rvCarList: RecyclerView
//    private lateinit var pbCars: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCarsBinding.inflate(inflater, container, false)
        return binding.root

        /*
                inflater.inflate(R.layout.fragment_cars, container, false).apply {
                    rvCarList = findViewById(R.id.rvCars)
                    pbCars = findViewById(R.id.pbCars)
                }.also {
                    return it
                }
        */
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        prepareRecyclerView()

        /*
                rvCarList.layoutManager = LinearLayoutManager(this.context)
                adapter = CarsAdapter(mutableListOf()) { car ->

                    val carDetailsFragment = CarDetailsFragment.newInstance(car)

                    // Replace the current fragment with CarDetailsFragment
                    parentFragmentManager.beginTransaction().apply {
                        replace(R.id.containerHomeFragment, carDetailsFragment)
                        addToBackStack(null)
                        commit()
                    }
                }
                rvCarList.adapter = adapter
        */

        binding.rvCars.hide()
        binding.pbCars.show()
        viewModel = ViewModelProvider(this)[CarsViewModel::class.java]
        viewModel.getCars()
        viewModel.carsLiveData.observe(viewLifecycleOwner, Observer { cars ->
            carsAdapter.updateData(cars as List<Result>)
            binding.pbCars.hide()
            binding.rvCars.show()

        })


        /*
                GlobalScope.launch(Dispatchers.Main) {
                    val carsAPiService: CarsApiService = CarsApiHelper.getInstance().create(
                        CarsApiService::class.java
                    )
                    rvCarList.hide()
                    pbCars.show()

                    val carsResponse = carsAPiService.getCars()
                    val carsList = carsAPiService.getCars()

                    adapter.updateData(carsResponse.body()?.results as List<Result>)

                    pbCars.hide()
                    rvCarList.show()


                }
        */

    }

    private fun prepareRecyclerView() {

        carsAdapter = CarsAdapter(mutableListOf()) { car ->

            val carDetailsFragment = CarDetailsFragment.newInstance(car)

            // Replace the current fragment with CarDetailsFragment
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.containerHomeFragment, carDetailsFragment)
                addToBackStack(null)
                commit()
            }
        }
        binding.rvCars.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = carsAdapter
        }
    }

    private fun View.show() {
        visibility = View.VISIBLE
    }

    private fun View.hide() {
        visibility = View.GONE
    }
}