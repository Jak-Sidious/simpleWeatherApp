package com.id.weatherapp.data.model

data class Current(
    val temp_c: Double,
    val condition: Condition,
    val humidity: Int,
    val uv: Double,
    val feelslike_c: Double
)
