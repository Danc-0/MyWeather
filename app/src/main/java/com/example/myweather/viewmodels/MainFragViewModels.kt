package com.example.myweather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.datasource.Resource
import com.example.movieapp.data.repository.WeatherRepository
import com.example.myweather.models.CurrentWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragViewModels @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val _myCurrentWeather: MutableLiveData<Resource<CurrentWeather>> = MutableLiveData()

    val currentWeather: LiveData<Resource<CurrentWeather>>
        get() = _myCurrentWeather

    fun getCurrentWeather(city: String) {
        viewModelScope.launch {
            _myCurrentWeather.value = Resource.Loading

            _myCurrentWeather.value = repository.getCurrentWeather(city)
        }
    }

}