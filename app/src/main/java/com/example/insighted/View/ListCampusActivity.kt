package com.example.insighted.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.R
import com.example.insighted.RecyclerViewCampusList
import com.example.insighted.model.kampus
import com.google.firebase.firestore.*

class ListCampusActivity : AppCompatActivity() {
    lateinit var recyclerViewCampusList: RecyclerView
    lateinit var back: ImageView
    private lateinit var kampusAdapter: RecyclerViewCampusList
    private val kampusList = ArrayList<kampus>()
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_campus)
        eventChangeListener()

        recyclerViewCampusList =findViewById(R.id.campus_list)
        recyclerViewCampusList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewCampusList.setHasFixedSize(true)
        kampusAdapter = RecyclerViewCampusList(kampusList)
        recyclerViewCampusList.adapter = kampusAdapter

        back = findViewById(R.id.BackBTN)
        back.setOnClickListener {
            onBackPressed()
        }
    }
    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("campus")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore error", error.message.toString())
                        return
                    }
                    for (dc in value?.documentChanges ?: emptyList()) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            kampusList.add(dc.document.toObject(kampus::class.java))
                        }
                    }
                    kampusAdapter.notifyDataSetChanged()
                }
            })
    }
}