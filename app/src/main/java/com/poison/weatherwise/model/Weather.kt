package com.poison.weatherwise.model

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)

data class Main(
    val temp: Double,
    val pressure: Int,
    val humidity: Int
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)


