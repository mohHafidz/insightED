package com.example.insighted.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.insighted.R

class questionActivity_Engineering : AppCompatActivity() {

    private lateinit var radioGroups: List<RadioGroup>
    lateinit var back: ImageView
    private val correctAnswers = listOf(
        R.id.radio_group1_button2,
        R.id.radio_group2_button2,
        R.id.radio_group3_button2,
        R.id.radio_group4_button2,
        R.id.radio_group5_button2,
        R.id.radio_group6_button2,
        R.id.radio_group7_button1,
        R.id.radio_group8_button1,
        R.id.radio_group9_button1,
        R.id.radio_group10_button2
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_list6)

        radioGroups = listOf(
            findViewById(R.id.radio_group1_engineering),
            findViewById(R.id.radio_group2_engineering),
            findViewById(R.id.radio_group3_engineering),
            findViewById(R.id.radio_group4_engineering),
            findViewById(R.id.radio_group5_engineering),
            findViewById(R.id.radio_group6_engineering),
            findViewById(R.id.radio_group7_engineering),
            findViewById(R.id.radio_group8_engineering),
            findViewById(R.id.radio_group9_engineering),
            findViewById(R.id.radio_group10_engineering)
        )

        back = findViewById(R.id.back_quiz)
        back.setOnClickListener {
            val intent = Intent(this, babpertanyaan::class.java)
            startActivity(intent)
        }

        val buttonNext: Button = findViewById(R.id.button_next_engineering)

        buttonNext.setOnClickListener {
            if (areAllQuestionsAnswered()) {
                val score = calculateScore()
                saveScore(score)

                // Tampilkan dialog konfirmasi
                AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah sudah yakin dengan jawaban anda?")
                    .setPositiveButton("Ya") { dialog, which ->
                        val intent = Intent(this, babpertanyaan::class.java)
                        startActivity(intent)
                    }
                    .setNegativeButton("Tidak") { dialog, which ->
                        // Tutup dialog
                        dialog.dismiss()
                    }
                    .show()
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
        editor.putInt("section_score_engineering", score)  // Save score for section 1
        editor.apply()
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