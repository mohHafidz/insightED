package com.example.insighted

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    private lateinit var radioGroups: List<RadioGroup>
    lateinit var back: ImageView

    private val correctAnswers = listOf(
        R.id.radio_group1_button3,
        R.id.radio_group2_button3,
        R.id.radio_group3_button4,
        R.id.radio_group4_button3,
        R.id.radio_group5_button3,
        R.id.radio_group6_button2,
        R.id.radio_group7_button2,
        R.id.radio_group8_button2,
        R.id.radio_group9_button3,
        R.id.radio_group10_button3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_list)

        radioGroups = listOf(
            findViewById(R.id.radio_group1),
            findViewById(R.id.radio_group2),
            findViewById(R.id.radio_group3),
            findViewById(R.id.radio_group4),
            findViewById(R.id.radio_group5),
            findViewById(R.id.radio_group6),
            findViewById(R.id.radio_group7),
            findViewById(R.id.radio_group8),
            findViewById(R.id.radio_group9),
            findViewById(R.id.radio_group10)
        )

        back = findViewById(R.id.back_quiz)
        back.setOnClickListener {
            val intent = Intent(this, babpertanyaan::class.java)
            startActivity(intent)
        }

        val buttonNext: Button = findViewById(R.id.button_next)
        buttonNext.setOnClickListener {
            if (areAllQuestionsAnswered()) {
                val score = calculateScore()
                saveScore(score)
                val intent = Intent(this, babpertanyaan::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please answer all questions", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun areAllQuestionsAnswered(): Boolean {
        for (radioGroup in radioGroups) {
            if (radioGroup.checkedRadioButtonId == -1) {
                return false
            }
        }
        return true
    }

    private fun saveScore(score: Int) {
        val sharedPreferences = getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("section_score_Accounting", score)
        editor.apply()
        // Verify score
        val savedScore = sharedPreferences.getInt("section_score_Accounting", -1)
        Log.d("QuestionActivity", "Score saved: $score, retrieved: $savedScore")
    }

    private fun calculateScore(): Int {
        var score = 0
        for (i in radioGroups.indices) {
            val selectedRadioButtonId = radioGroups[i].checkedRadioButtonId
            if (selectedRadioButtonId == correctAnswers[i]) {
                score += 2
            } else {
                score -= 1
            }
        }
        return score
    }
}
