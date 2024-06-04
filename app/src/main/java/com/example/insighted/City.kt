package com.example.insighted

data class CityResponse(
    val code: String,
    val messages: String,
    val value: List<City>
)

data class City(
    val id: String,
    val id_provinsi: Int,
    val name: String
)
