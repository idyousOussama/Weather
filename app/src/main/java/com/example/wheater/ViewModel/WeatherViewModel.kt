package com.example.wheater.ViewModel

import androidx.lifecycle.ViewModel
import com.example.wheater.Repository.WeatherRepository
import com.example.wheater.Server.ApiClient
import com.example.wheater.Server.ApiServices

class WeatherViewModel (val repository: WeatherRepository) : ViewModel(){
    constructor():this(WeatherRepository(ApiClient().getClient().create(ApiServices::class.java)))

    fun loadCurrentWeather (lat : Double , lon : Double , unit: String) = repository.getCurrentWeather(lat, lon, unit)
}