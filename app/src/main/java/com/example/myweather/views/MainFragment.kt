package com.example.myweather.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.utils.ReadError
import com.example.myweather.R
import com.example.myweather.models.CurrentWeather
import com.example.myweather.viewmodels.MainFragViewModels
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@AndroidEntryPoint
@FragmentScoped
class MainFragment : Fragment(R.layout.fragment_main) {

    private val TAG = "MainFragment"
    private val viewModel by viewModels<MainFragViewModels>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCurrentWeather()

    }

    fun getCurrentWeather() {
        viewModel.getCurrentWeather()

        viewModel.currentWeather.observe(viewLifecycleOwner, {

            when(it) {

                is Resource.Success -> {
                    lifecycleScope.launch {
                        val currentWeather: CurrentWeather = it.value
                        Log.d(TAG, "getCurrentWeather: $currentWeather")
                    }
                }
                
                is Resource.Failure -> {
                    val responseBody: ResponseBody? = it.errorBody
                    val error: String = ReadError().readError(responseBody)
                    Log.d(TAG, "getCurrentWeather: $error")
                }

            }

        })
    }

}