package com.example.insighted.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.insighted.R

class recyclerViewPsikolog(private val data: ArrayList<psikolog>) :
    RecyclerView.Adapter<recyclerViewPsikolog.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foto: ImageView = itemView.findViewById(R.id.psikologIMG)
        val nama: TextView = itemView.findViewById(R.id.namaPsikolog)
        val jabatan: TextView = itemView.findViewById(R.id.jabatanPsikolog)
        val harga: TextView = itemView.findViewById(R.id.harga)
        val pengalaman: TextView = itemView.findViewById(R.id.pengalaman)
        val rating: TextView = itemView.findViewById(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.psikolog_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = data[position]
        holder.nama.text = currentItem.nama
        holder.jabatan.text = currentItem.jabatan
        holder.harga.text = "Rp ${currentItem.harga}"
        holder.pengalaman.text = "${currentItem.pengalaman} tahun"
        holder.rating.text = "${currentItem.rating} %"

        Glide.with(holder.foto.context)
            .load(currentItem.foto)
            .into(holder.foto)
    }
}
