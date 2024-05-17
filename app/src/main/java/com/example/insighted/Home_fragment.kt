package com.example.insighted

import  android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.View.recyclerViewBeasiswa
import com.example.insighted.View.recyclerViewKampus
import com.example.insighted.model.beasiswaManager
import com.example.insighted.model.kampusManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Home_fragment : Fragment() {

    lateinit var recyclerViewKampus: RecyclerView
    lateinit var recyclerViewBeasiswa: RecyclerView
    lateinit var viewCampus: TextView
    lateinit var viewBeasiswa: TextView
    lateinit var userTextView: TextView
    private lateinit var auth: FirebaseAuth
    lateinit var akun: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
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

        userTextView = view.findViewById(R.id.username)
        val currentUser = auth.currentUser
        if (currentUser != null) {
            setUserName(currentUser)
        } else {
            userTextView.text = "Fardan Titi \uD83D\uDC4B"
        }

        akun = view.findViewById(R.id.account)

        akun.setOnClickListener {
            val bottomSheetFragment = CustomBottomSheetDialogFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

        return view
    }

    fun setUserName(user: FirebaseUser){
        val displayName = user.displayName
        if (displayName != null) {
            userTextView.text = "$displayName \uD83D\uDC4B"
        } else {
            userTextView.text = "Fardan Titi \uD83D\uDC4B"
        }
    }
}
