package com.example.insighted

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.View.recyclerViewBeasiswa
import com.example.insighted.View.recyclerViewSholarship
import com.example.insighted.model.beasiswa
import com.example.insighted.model.beasiswaManager
import com.google.firebase.firestore.*

class ListBeasiswa : AppCompatActivity() {
    lateinit var recyclerViewBeasiswa: RecyclerView
    lateinit var back: ImageView
    private lateinit var db: FirebaseFirestore
    private lateinit var beasiswaAdapter: recyclerViewSholarship
    private val beasiwaList = ArrayList<beasiswa>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_beasiswa)
        eventChangeListener()

        recyclerViewBeasiswa = findViewById(R.id.sholarship_list)
        recyclerViewBeasiswa.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewBeasiswa.setHasFixedSize(true)
        beasiswaAdapter = recyclerViewSholarship(beasiwaList)
        recyclerViewBeasiswa.adapter = beasiswaAdapter


        back = findViewById(R.id.backBTN)
        back.setOnClickListener {
            onBackPressed()
        }
    }
    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("scholarship")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore error", error.message.toString())
                        return
                    }
                    for (dc in value?.documentChanges ?: emptyList()) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            beasiwaList.add(dc.document.toObject(beasiswa::class.java))
                        }
                    }
                    beasiswaAdapter.notifyDataSetChanged()
                }
            })
    }
}