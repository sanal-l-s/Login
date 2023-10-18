package com.example.login.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.data.Result
import com.example.login.data.VehicleType

class CarDetailsFragment : Fragment() {
    private lateinit var txtCommonName: TextView
    private lateinit var txtCarName: TextView
    private lateinit var txtCarCountry: TextView
    private lateinit var txtMfdId: TextView
    private lateinit var rvVehicleTypes: RecyclerView

    companion object {
        // Function to create a new instance of CarDetailsFragment with car data
        fun newInstance(car: Result): CarDetailsFragment {
            val fragment = CarDetailsFragment()
            val args = Bundle()
            args.putParcelable("carData", car)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_details, container, false).apply {
            txtCommonName = findViewById(R.id.txtCarCommonName)
            txtCarName = findViewById(R.id.txtCarName)
            txtCarCountry = findViewById(R.id.txtCarCountry)
            txtMfdId = findViewById(R.id.txtMfdId)
            rvVehicleTypes = findViewById(R.id.rvVehicleTypes)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val car = arguments?.getParcelable<Result>("carData")!!
        val car = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("carData", Result::class.java)!!
        } else {
            arguments?.getParcelable<Result>("carData")!!
        }

        txtCommonName.text = car.mfrCommonName
        txtCarName.text = car.mfrName
        txtMfdId.text = car.mfrID.toString()
        txtCarCountry.text = car.country

        rvVehicleTypes.layoutManager = LinearLayoutManager(this.context)
        rvVehicleTypes.adapter = VehicleTypeAdapter(car.vehicleTypes as List<VehicleType>)

    }

}