package com.example.login.modules.homepage.cars.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.model.cars.Result
import com.example.login.network.CarsApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarsViewModel : ViewModel() {
    var carsLiveData = MutableLiveData<List<Result?>>()
    fun getCars() {
        viewModelScope.launch(Dispatchers.Main) {
            CarsApiHelper.api.getCars().apply {
                if (isSuccessful) {
                    carsLiveData.value = body()?.results!!
                } else {
                    Log.i("getCars: ", errorBody().toString())
                }
            }

        }
    }
}