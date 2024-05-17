package com.example.insighted.model

class beasiswa {
    var id : Int = 0
    var nama : String = ""
    var desk : String = ""
    var gambar : Int = 0

    fun addBeasiswa(nama : String, desk : String, gambar : Int){
        this.nama = nama
        this.desk = desk
        this.gambar = gambar
        this.id = beasiswaManager.getBeasiswaList().size + 1
    }
}