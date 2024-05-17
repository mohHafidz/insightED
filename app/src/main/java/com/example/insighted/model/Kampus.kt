package com.example.insighted.model

class kampus {
    var id : Int = 0
    var nama : String = ""
    var lokasi : String = ""
    var akreditasi : String = ""
    var gambar : Int = 0
    var logo : Int = 0

    fun addKampus (nama : String, lokasi : String, akreditasi : String, gambar : Int, logo : Int){
        this.nama = nama
        this.lokasi = lokasi
        this.akreditasi = akreditasi
        this.gambar = gambar
        this.logo = logo
        this.id = kampusManager.getKampusList().size + 1
    }
}