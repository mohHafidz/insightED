package com.example.insighted

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.insighted.model.kampus

class RecyclerViewCampusList(private val dataList: List<kampus>) : RecyclerView.Adapter<RecyclerViewCampusList.ViewHolder>() {

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
        val currentItem = dataList[position]
        holder.nama_kampus.text = currentItem.nama
        holder.lokasi_kampus.text = currentItem.lokasi
        holder.grade_kampus.text = currentItem.akreditasi
//        holder.logo_kampus.setImageResource(dataList[position].logo)

        Glide.with(holder.logo_kampus.context)
            .load(currentItem.logo)
            .into(holder.logo_kampus)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

