package com.example.insighted

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class quizFragment : Fragment() {

    lateinit var topik: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        topik = view.findViewById(R.id.button_start)

        topik.setOnClickListener {
            val intent = Intent(requireContext(), babpertanyaan::class.java)
            startActivity(intent)
        }

        return view
    }
}