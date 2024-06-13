package com.example.insighted

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var jurusan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        jurusan = findViewById(R.id.jurusan)
        compareScores()
    }

    private fun compareScores() {
        val sharedPreferences = getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)

        // Retrieve scores for each section
        val accountingScore = sharedPreferences.getInt("section_score_Accounting", 0)
        val artScore = sharedPreferences.getInt("section_score_art", 0)
        val businessScore = sharedPreferences.getInt("section_score_business", 0)
        val communicationScore = sharedPreferences.getInt("section_score_communication", 0)
        val computingScore = sharedPreferences.getInt("section_score_computing", 0)
        val engineeringScore = sharedPreferences.getInt("section_score_engineering", 0)
        val hospitalityScore = sharedPreferences.getInt("section_score_hospitality", 0)
        val humanitiesScore = sharedPreferences.getInt("section_score_humanities", 0)

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

        // Display the department with the highest score
        jurusan.text = "$highestScoreJurusan"
    }
}
