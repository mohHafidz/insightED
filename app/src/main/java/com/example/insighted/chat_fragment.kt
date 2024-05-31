package com.example.insighted

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.insighted.View.recyclerViewBeasiswa
import com.example.insighted.View.recyclerViewKampus
import com.example.insighted.model.beasiswa
import com.example.insighted.model.kampus
import com.example.insighted.model.psikolog
import com.example.insighted.model.recyclerViewPsikolog
import com.google.firebase.firestore.*


class chat_fragment : Fragment() {
    private lateinit var recyclerViewPsikolog: RecyclerView
    private lateinit var psikologAdapter: recyclerViewPsikolog
    private val psikologList = ArrayList<psikolog>()
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat_fragment, container, false)
        initUI(view)
        setupRecyclerViews(view)
        eventChangeListener()
        return view
    }

    private fun initUI(view: View) {
        recyclerViewPsikolog = view.findViewById(R.id.recycler_psikolog)
    }

    private fun setupRecyclerViews(view: View) {
        recyclerViewPsikolog.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewPsikolog.setHasFixedSize(true)
        psikologAdapter = recyclerViewPsikolog(psikologList)
        recyclerViewPsikolog.adapter = psikologAdapter
    }

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("psikolog")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore error", error.message.toString())
                        return
                    }
                    for (dc in value?.documentChanges ?: emptyList()) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            psikologList.add(dc.document.toObject(psikolog::class.java))
                        }
                    }
                    psikologAdapter.notifyDataSetChanged()
                }
            })
    }
}