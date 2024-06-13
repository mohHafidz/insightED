package com.example.insighted

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class babpertanyaan : AppCompatActivity() {

    lateinit var accounting: ConstraintLayout
    lateinit var art: ConstraintLayout
    lateinit var business: ConstraintLayout
    lateinit var communication: ConstraintLayout
    lateinit var computing: ConstraintLayout
    lateinit var engineering: ConstraintLayout
    lateinit var hospitality: ConstraintLayout
    lateinit var humanities: ConstraintLayout
    lateinit var submit: Button
    lateinit var back_fragment: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_babpertanyaan)

        accounting = findViewById(R.id.accounting)
        art = findViewById(R.id.art)
        business = findViewById(R.id.business)
        communication = findViewById(R.id.communication)
        computing = findViewById(R.id.computing)
        engineering = findViewById(R.id.engineering)
        hospitality = findViewById(R.id.hospitality)
        humanities = findViewById(R.id.humanities)
        submit = findViewById(R.id.resultButton)
        back_fragment = findViewById(R.id.back_quiz)

        submit.setOnClickListener {
            val bottomSheetFragment = bottomSheetResult()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        back_fragment.setOnClickListener {
            onBackPressed()
        }

        // Check and disable buttons if score exists
        checkAndDisableButton("section_score_Accounting", accounting)
        checkAndDisableButton("section_score_art", art)
        checkAndDisableButton("section_score_business", business)
        checkAndDisableButton("section_score_communication", communication)
        checkAndDisableButton("section_score_computing", computing)
        checkAndDisableButton("section_score_engineering", engineering)
        checkAndDisableButton("section_score_hospitality", hospitality)
        checkAndDisableButton("section_score_humanities", humanities)

        // Check if submit button can be enabled
        checkSubmitButton()

        accounting.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
        art.setOnClickListener {
            val intent = Intent(this, questionActivity_art::class.java)
            startActivity(intent)
        }
        business.setOnClickListener {
            val intent = Intent(this, questionActivity_business::class.java)
            startActivity(intent)
        }
        communication.setOnClickListener {
            val intent = Intent(this, questionActivity_Communication::class.java)
            startActivity(intent)
        }
        computing.setOnClickListener {
            val intent = Intent(this, questionActivity_Computing::class.java)
            startActivity(intent)
        }
        engineering.setOnClickListener {
            val intent = Intent(this, questionActivity_Engineering::class.java)
            startActivity(intent)
        }
        hospitality.setOnClickListener {
            val intent = Intent(this, questionActivity_Hospitality::class.java)
            startActivity(intent)
        }
        humanities.setOnClickListener {
            val intent = Intent(this, questionActivity_Humanities::class.java)
            startActivity(intent)
        }
    }

    private fun checkAndDisableButton(scoreKey: String, button: ConstraintLayout) {
        val sharedPreferences = getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)
        val score = sharedPreferences.getInt(scoreKey, 0)
        if (score != 0) {
            button.isEnabled = false
            button.setBackgroundColor(Color.GRAY)
        }
    }

    private fun checkSubmitButton() {
        val sharedPreferences = getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)
        val accountingScore = sharedPreferences.getInt("section_score_Accounting", 0)
        val artScore = sharedPreferences.getInt("section_score_art", 0)
        val businessScore = sharedPreferences.getInt("section_score_business", 0)
        val communicationScore = sharedPreferences.getInt("section_score_communication", 0)
        val computingScore = sharedPreferences.getInt("section_score_computing", 0)
        val engineeringScore = sharedPreferences.getInt("section_score_engineering", 0)
        val hospitalityScore = sharedPreferences.getInt("section_score_hospitality", 0)
        val humanitiesScore = sharedPreferences.getInt("section_score_humanities", 0)

        // Check if all scores are set
        if (accountingScore != 0 && artScore != 0 && businessScore != 0 && communicationScore != 0 &&
            computingScore != 0 && engineeringScore != 0 && hospitalityScore != 0 && humanitiesScore != 0) {
            submit.isEnabled = true
            submit.setBackgroundColor(Color.GREEN) // Optional: Change color to indicate it's enabled
        } else {
            submit.isEnabled = false
            submit.setBackgroundColor(Color.GRAY) // Optional: Change color to indicate it's disabled
        }
    }
}
