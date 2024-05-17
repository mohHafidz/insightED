package com.example.insighted

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.model.kampus

class RecyclerViewCampusList(private val context: Context, private val dataList: List<kampus>) : RecyclerView.Adapter<RecyclerViewCampusList.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama_kampus: TextView = itemView.findViewById(R.id.nama_kampus)
        val lokasi_kampus: TextView = itemView.findViewById(R.id.lokasi_kampus)
        val logo_kampus: ImageView = itemView.findViewById(R.id.logo_kampus)
        val grade_kampus: TextView = itemView.findViewById(R.id.grade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.campus_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama_kampus.text = dataList[position].nama
        holder.lokasi_kampus.text = dataList[position].lokasi
        holder.grade_kampus.text = dataList[position].akreditasi
        holder.logo_kampus.setImageResource(dataList[position].logo)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

