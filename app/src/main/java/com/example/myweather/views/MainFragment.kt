package com.example.myweather.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.utils.ReadError
import com.example.myweather.R
import com.example.myweather.adapters.MainAdapter
import com.example.myweather.models.CurrentWeather
import com.example.myweather.viewmodels.MainFragViewModels
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@AndroidEntryPoint
@FragmentScoped
class MainFragment : Fragment(R.layout.fragment_main) {

    private val TAG = "MainFragment"
    private val viewModel by viewModels<MainFragViewModels>()
    private lateinit var mainAdapter: MainAdapter
    var weatherList: MutableList<CurrentWeather> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        circleImageView2.setOnClickListener {
            getCurrentWeather(cityNameChoice.text.toString())
//            Log.d(TAG, "onViewCreated: ${}")
        }
    }

    fun getCurrentWeather(cityName: String) {
        viewModel.getCurrentWeather(cityName)

        viewModel.currentWeather.observe(viewLifecycleOwner, {

            when(it) {

                is Resource.Success -> {
                    lifecycleScope.launch {
                        val currentWeather: CurrentWeather = it.value
                        weatherList.add(currentWeather)
                        mainAdapter = MainAdapter(weatherList)

                        rvCurrentWeather.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                        rvCurrentWeather.adapter = mainAdapter
                    }

                    mainAdapter.apply {
                        true
                        notifyDataSetChanged()
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