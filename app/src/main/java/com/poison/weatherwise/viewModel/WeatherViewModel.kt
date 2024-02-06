package com.poison.weatherwise.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poison.weatherwise.model.WeatherResponse
import com.poison.weatherwise.network.ApiClient
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private val apiKey = "b332482da25cb468cb5c2b951f38ab4e"
    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    fun getWeatherByCity(city: String){
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getWeather(city, apiKey)
                _weather.postValue(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun getImageUrl(iconCode: String): String{
        return "https://openweathermap.org/img/wn/${iconCode}@2x.png"
    }
    

}