package com.example.insighted

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.insighted.model.beasiswa
import com.example.insighted.model.beasiswaManager
import com.example.insighted.model.kampus
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class HomeFragment : Fragment() {

    private lateinit var recyclerViewKampus: RecyclerView
    private lateinit var recyclerViewBeasiswa: RecyclerView
    private lateinit var viewCampus: TextView
    private lateinit var viewBeasiswa: TextView
    private lateinit var userTextView: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var akun: ImageView
    private lateinit var db: FirebaseFirestore
    private lateinit var kampusAdapter: recyclerViewKampus
    private lateinit var beasiswaAdapter: recyclerViewBeasiswa
    private val kampusList = ArrayList<kampus>()
    private val beasiwaList = ArrayList<beasiswa>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_fragment, container, false)

        auth = FirebaseAuth.getInstance()

        initUI(view)
        setupRecyclerViews(view)
        loadUserDetails()
        setupEventListeners()

        return view
    }

    private fun initUI(view: View) {
        recyclerViewKampus = view.findViewById(R.id.recycler_kampus)
        recyclerViewBeasiswa = view.findViewById(R.id.recyclerView_beasiswa)
        viewCampus = view.findViewById(R.id.Detail_kampusTV)
        viewBeasiswa = view.findViewById(R.id.viewAll_beasiswa)
        userTextView = view.findViewById(R.id.username)
        akun = view.findViewById(R.id.account)
    }

    private fun setupRecyclerViews(view: View) {
        recyclerViewKampus.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewKampus.setHasFixedSize(true)
        kampusAdapter = recyclerViewKampus(kampusList)
        recyclerViewKampus.adapter = kampusAdapter

        recyclerViewBeasiswa.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewBeasiswa.setHasFixedSize(true)
        beasiswaAdapter = recyclerViewBeasiswa(beasiwaList)
        recyclerViewBeasiswa.adapter = beasiswaAdapter
    }

    private fun loadUserDetails() {
        val currentUser = auth.currentUser
        setUserName(currentUser)
    }

    private fun setupEventListeners() {
        viewCampus.setOnClickListener {
            startActivity(Intent(requireContext(), ListCampusActivity::class.java))
        }

        viewBeasiswa.setOnClickListener {
            startActivity(Intent(requireContext(), ListBeasiswa::class.java))
        }

        akun.setOnClickListener {
            val bottomSheetFragment = CustomBottomSheetDialogFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

        eventChangeListener()
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

    private fun setUserName(user: FirebaseUser?) {
        val displayName = user?.displayName ?: "Fardan Titi"
        userTextView.text = "$displayName \uD83D\uDC4B"
    }
}
