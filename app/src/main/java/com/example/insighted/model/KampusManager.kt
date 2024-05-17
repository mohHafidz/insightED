package com.example.insighted.model

object kampusManager {
    private val kampusList = ArrayList<kampus>()

    fun addKampus(kampus: kampus){
        kampusList.add(kampus)
    }

    fun getKampusList(): ArrayList<kampus> {
        return kampusList
    }
}