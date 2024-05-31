package com.example.insighted.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.insighted.R
import com.example.insighted.model.beasiswa

class recyclerViewBeasiswa (private val data: ArrayList<beasiswa>) :
    RecyclerView.Adapter<recyclerViewBeasiswa.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama_beasiswa: TextView = itemView.findViewById(R.id.nama_beasiswa)
        val gambar_beasiswa: ImageView = itemView.findViewById(R.id.gambar_beasiswa)
        val desk_beasiswa: TextView = itemView.findViewById(R.id.deskripsi_beasiswa)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.beasiswa_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nama_beasiswa.text = data[position].nama
        holder.desk_beasiswa.text = data[position].desk
//        holder.gambar_beasiswa.setImageResource(data[position].gambar)
        Glide.with(holder.gambar_beasiswa.context)
            .load(data[position].gambar)
            .into(holder.gambar_beasiswa)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}