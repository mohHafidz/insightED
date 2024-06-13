package com.example.insighted

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class bottomSheetResult : BottomSheetDialogFragment() {

    private lateinit var jurusan: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_bottom_sheet_result, container, false)
        jurusan = view.findViewById(R.id.jurusan)
        compareScores()
        return view
    }

    private fun compareScores() {
        val sharedPreferences = requireContext().getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)

        // Retrieve scores for each section
        val accountingScore = sharedPreferences.getInt("section_score_Accounting", -1)
        val artScore = sharedPreferences.getInt("section_score_art", -1)
        val businessScore = sharedPreferences.getInt("section_score_business", -1)
        val communicationScore = sharedPreferences.getInt("section_score_communication", -1)
        val computingScore = sharedPreferences.getInt("section_score_computing", -1)
        val engineeringScore = sharedPreferences.getInt("section_score_engineering", -1)
        val hospitalityScore = sharedPreferences.getInt("section_score_hospitality", -1)
        val humanitiesScore = sharedPreferences.getInt("section_score_humanities", -1)

        // Compare scores to determine the highest
        var highestScore = -11
        var highestScoreJurusan = "None"

        // Check each score and update highestScoreJurusan accordingly
        if (accountingScore > highestScore) {
            highestScore = accountingScore
            highestScoreJurusan = "Accounting"
        }
        if (artScore > highestScore) {
            highestScore = artScore
            highestScoreJurusan = "Art"
        }
        if (businessScore > highestScore) {
            highestScore = businessScore
            highestScoreJurusan = "Business"
        }
        if (communicationScore > highestScore) {
            highestScore = communicationScore
            highestScoreJurusan = "Communication"
        }
        if (computingScore > highestScore) {
            highestScore = computingScore
            highestScoreJurusan = "Computing"
        }
        if (engineeringScore > highestScore) {
            highestScore = engineeringScore
            highestScoreJurusan = "Engineering"
        }
        if (hospitalityScore > highestScore) {
            highestScore = hospitalityScore
            highestScoreJurusan = "Hospitality"
        }
        if (humanitiesScore > highestScore) {
            highestScore = humanitiesScore
            highestScoreJurusan = "Humanities"
        }

        // If all scores are -1, display a message indicating no scores saved
        if (accountingScore == -1 && artScore == -1 && businessScore == -1 &&
            communicationScore == -1 && computingScore == -1 && engineeringScore == -1 &&
            hospitalityScore == -1 && humanitiesScore == -1) {
            highestScoreJurusan = "No scores saved"
        }

        // Display the department with the highest score
        jurusan.text = "$highestScoreJurusan"
    }
}
