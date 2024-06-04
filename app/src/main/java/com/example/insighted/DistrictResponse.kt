package com.example.insighted

data class DistrictResponse(
    val code: String,
    val messages: String,
    val value: List<District>
)

data class District(
    val id: String,
    val id_kabupaten: String,
    val name: String
)
