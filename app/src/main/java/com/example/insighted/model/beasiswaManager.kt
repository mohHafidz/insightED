package com.example.insighted.model

object beasiswaManager {
    private val beasiswaList = ArrayList<beasiswa>()

    fun addBeasiswa(beasiswa: beasiswa){
        beasiswaList.add(beasiswa)
    }

    fun getBeasiswaList(): ArrayList<beasiswa> {
        return beasiswaList
    }
}