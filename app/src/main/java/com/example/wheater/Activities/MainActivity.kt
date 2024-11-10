package com.example.wheater.Activities

import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wheater.Module.City
import com.example.wheater.Module.CurrentResponceApi
import com.example.wheater.R
import com.example.wheater.ViewModel.WeatherViewModel
import com.example.wheater.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response
import java.io.InputStreamReader
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
lateinit var   binding :ActivityMainBinding
private val weatherViewModel: WeatherViewModel by viewModels()
    private val calender by lazy { Calendar.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val citiesList = loadCitiesFromJson()

        val cityNames = citiesList.map { it.name }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.citiesSpinner.adapter = adapter


        binding.citiesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCity = citiesList[position]
                getCityWeather(selectedCity.latitude ,  selectedCity.longitude)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }
    private fun getCityWeather(lat: Double, lon: Double) {
        binding.apply {
            var lot = lat
            var lon = lon
            weatherViewModel.loadCurrentWeather(lot, lon, "metric")
                .enqueue(object : retrofit2.Callback<CurrentResponceApi> {
                    override fun onResponse(
                        p0: Call<CurrentResponceApi>,
                        p1: Response<CurrentResponceApi>
                    ) {
                        if (p1.isSuccessful) {
                            var data = p1.body()
                            data.let {
                                mainDesWeather.text = it!!.weather.get(0).main ?: "-"
                                windCounter.text =
                                    it.wind.speed.let { Math.round(it).toString() } + "Km"
                                mainTemperatureNum.text =
                                    it.main.temp.let { Math.round(it).toString() } + "°"
                                mainDownTemperature.text =
                                    it.main.tempMin.let { Math.round(it).toString() } + "°"
                                mainUpTemperature.text =
                                    it.main.tempMax.let { Math.round(it).toString() } + "°"
                                humididtyCounter.text = it.main.humidity.toString() + "%"
                                if (ifNightNow()) {
                                    main.setBackgroundResource(R.drawable.night_screen)
                                    Toast.makeText(baseContext, "night", Toast.LENGTH_SHORT).show()
                                } else {
                                    main.setBackgroundResource(R.drawable.day_screen)
                                }
                            }
                        }
                    }
                    override fun onFailure(p0: Call<CurrentResponceApi>, p1: Throwable) {
                        Toast.makeText(
                            baseContext,
                            "UpLoad the city Weather is failure please try again",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                })
        }


    }

private fun ifNightNow(): Boolean{
    return calender.get(Calendar.HOUR_OF_DAY) > 18
}
private fun loadCitiesFromJson(): List<City> {
    val inputStream = assets.open("moroccan_cities.json")
    val reader = InputStreamReader(inputStream)
    val cityType = object : TypeToken<List<City>>() {}.type
    return Gson().fromJson(reader, cityType)
}

}