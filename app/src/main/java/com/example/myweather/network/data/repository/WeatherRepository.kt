package com.example.movieapp.data.repository

import com.example.movieapp.data.datasource.ApiHelper
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiHelper: ApiHelper): BaseRepository() {

    suspend fun getCurrentWeather() =
        safeApiCall {
            apiHelper.getCurrentWeather()
        }

}