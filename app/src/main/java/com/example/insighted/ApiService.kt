package com.example.insighted

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("wilayah/provinsi")
    fun getProvinces(
        @Query("api_key") apiKey: String
    ): Call<ProvinceResponse>

    @GET("wilayah/kabupaten")
    fun getCities(
        @Query("api_key") apiKey: String,
        @Query("id_provinsi") provinceId: String
    ): Call<CityResponse>

    @GET("wilayah/kecamatan")
    fun getDistricts(
        @Query("api_key") apiKey: String,
        @Query("id_kabupaten") cityId: String
    ): Call<DistrictResponse>
}
