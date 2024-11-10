package com.example.wheater.Repository

import com.example.wheater.Server.ApiServices

class WeatherRepository (var api : ApiServices){
    fun getCurrentWeather(lat : Double , lon :Double , unit :String) = api.getCurrentWeather(lat,lon,unit,"d7258c2de69f8a4ca64ea550abd7fa92")

}

