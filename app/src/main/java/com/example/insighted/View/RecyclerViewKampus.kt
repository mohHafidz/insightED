package com.example.insighted.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.R
import com.example.insighted.model.kampus

class recyclerViewKampus(private val data: ArrayList<kampus>) :
    RecyclerView.Adapter<recyclerViewKampus.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama_kampus: TextView = itemView.findViewById(R.id.nama_universitas)
        val gambar_kampus: ImageView = itemView.findViewById(R.id.gambar_universitas)
        val lokasi_kampus: TextView = itemView.findViewById(R.id.lokasi_universitas)
        val akreditasi_kampus: TextView = itemView.findViewById(R.id.akreditasi_unversitas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kampus_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nama_kampus.text = data[position].nama
        holder.gambar_kampus.setImageResource(data[position].gambar)
        holder.lokasi_kampus.text = data[position].lokasi
        holder.akreditasi_kampus.text = data[position].akreditasi
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
