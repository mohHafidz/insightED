package com.example.insighted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.View.MainActivity
import com.example.insighted.model.kampusManager

class ListCampusActivity : AppCompatActivity() {
    lateinit var recyclerViewCampusList: RecyclerView
    lateinit var back: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_campus)

        recyclerViewCampusList =findViewById(R.id.campus_list)
        recyclerViewCampusList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val data = kampusManager.getKampusList() // Anda perlu mendeklarasikan kampusManager terlebih dahulu
        val kampusAdapter = RecyclerViewCampusList(this, data) // Menggunakan constructor adapter yang telah dibuat
        recyclerViewCampusList.adapter = kampusAdapter

        back = findViewById(R.id.BackBTN)
        back.setOnClickListener {
            onBackPressed()
        }
    }
}