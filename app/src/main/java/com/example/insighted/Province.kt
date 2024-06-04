// Province.kt
package com.example.insighted

data class ProvinceResponse(
    val code: String,
    val messages: String,
    val value: List<Province>
)

data class Province(
    val id: String,
    val name: String
)
