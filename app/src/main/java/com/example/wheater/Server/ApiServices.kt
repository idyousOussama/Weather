package com.example.wheater.Server

import com.example.wheater.Module.CurrentResponceApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather")
fun getCurrentWeather(
    @Query("lat") late :Double,
    @Query("lon") lon : Double,
    @Query("unit") unit : String,
    @Query("appid") ApiKey : String,
): Call<CurrentResponceApi>


}