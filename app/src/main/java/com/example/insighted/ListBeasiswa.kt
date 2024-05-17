package com.example.insighted

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.View.recyclerViewBeasiswa
import com.example.insighted.model.beasiswaManager

class ListBeasiswa : AppCompatActivity() {
    lateinit var recyclerViewBeasiswa: RecyclerView
    lateinit var back: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_beasiswa)

        recyclerViewBeasiswa = findViewById(R.id.sholarship_list)
        recyclerViewBeasiswa.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val data2 = beasiswaManager.getBeasiswaList()
        val beasiswaAdapter = recyclerViewBeasiswa(data2)
        recyclerViewBeasiswa.adapter = beasiswaAdapter


        back = findViewById(R.id.backBTN)
        back.setOnClickListener {
            onBackPressed()
        }
    }
}