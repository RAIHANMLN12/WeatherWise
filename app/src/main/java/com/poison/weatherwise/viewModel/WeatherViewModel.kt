package com.poison.weatherwise.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    fun getWeatherByCity(city: String){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiClient.apiService.getWeather(city, apiKey)
                _weather.postValue(response)
            } catch (e: Exception) {
                // Handle error
                Log.e("WeatherApp", "Error getting weather by city", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getImageUrl(iconCode: String): String{
        return "https://openweathermap.org/img/wn/${iconCode}@2x.png"
    }


}