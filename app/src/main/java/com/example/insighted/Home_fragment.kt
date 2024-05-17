package com.example.insighted

import  android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.View.recyclerViewBeasiswa
import com.example.insighted.View.recyclerViewKampus
import com.example.insighted.model.beasiswaManager
import com.example.insighted.model.kampusManager

class Home_fragment : Fragment() {

    lateinit var recyclerViewKampus: RecyclerView
    lateinit var recyclerViewBeasiswa: RecyclerView
    lateinit var viewCampus: TextView
    lateinit var viewBeasiswa: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_fragment, container, false)

        recyclerViewKampus = view.findViewById(R.id.recycler_kampus)
        recyclerViewKampus.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val data = kampusManager.getKampusList()
        val kampusAdapter = recyclerViewKampus(data)
        recyclerViewKampus.adapter = kampusAdapter

        recyclerViewBeasiswa = view.findViewById(R.id.recyclerView_beasiswa)
        recyclerViewBeasiswa.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val data2 = beasiswaManager.getBeasiswaList()
        val beasiswaAdapter = recyclerViewBeasiswa(data2)
        recyclerViewBeasiswa.adapter = beasiswaAdapter


        viewCampus = view.findViewById(R.id.Detail_kampusTV)
        viewCampus.setOnClickListener {
            val intent = Intent(requireContext(), ListCampusActivity::class.java)
            startActivity(intent)
        }


        viewBeasiswa = view.findViewById(R.id.viewAll_beasiswa)
        viewBeasiswa.setOnClickListener {
            val intent = Intent(requireContext(), ListBeasiswa::class.java)
            startActivity(intent)
        }
        return view
    }
}
