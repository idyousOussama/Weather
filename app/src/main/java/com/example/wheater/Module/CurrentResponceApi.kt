package com.example.wheater.Module


import com.example.wheater.Activities.Clouds
import com.example.wheater.Activities.Coord
import com.example.wheater.Activities.Main
import com.example.wheater.Activities.Rain
import com.example.wheater.Activities.Sys
import com.example.wheater.Activities.Weather
import com.example.wheater.Activities.Wind
import com.google.gson.annotations.SerializedName

data class CurrentResponceApi(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)