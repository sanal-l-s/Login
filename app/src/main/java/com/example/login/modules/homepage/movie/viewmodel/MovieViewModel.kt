package com.example.login.modules.homepage.movie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.modules.homepage.movie.model.Movies
import com.example.login.modules.homepage.movie.model.Result
import com.example.login.modules.homepage.movie.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
	var movieLiveData = MutableLiveData<List<Result>>()
	fun getPopularMovies() {
		RetrofitInstance.api.getPopularMovies("69d66957eebff9666ea46bd464773cf0").enqueue(object :
			Callback<Movies> {
			override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
				if (response.body() != null) {
					movieLiveData.value = response.body()!!.results
				} else {
					return
				}
			}

			override fun onFailure(call: Call<Movies>, t: Throwable) {
				Log.d("TAG", t.message.toString())
			}
		})
	}

	fun observeMovieLiveData(): LiveData<List<Result>> {
		return movieLiveData
	}
} 
