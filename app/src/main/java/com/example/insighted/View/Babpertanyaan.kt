package com.example.insighted.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.insighted.R

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
    lateinit var goBack : ImageView

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
        goBack = findViewById(R.id.back_quiz)

        goBack.setOnClickListener {
            onBackPressed()
        }


        submit.setOnClickListener {
            val bottomSheetFragment = bottomSheetResult()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
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
            finish()
        }
        art.setOnClickListener {
            val intent = Intent(this, questionActivity_art::class.java)
            startActivity(intent)
            finish()
        }
        business.setOnClickListener {
            val intent = Intent(this, questionActivity_business::class.java)
            startActivity(intent)
            finish()
        }
        communication.setOnClickListener {
            val intent = Intent(this, questionActivity_Communication::class.java)
            startActivity(intent)
            finish()
        }
        computing.setOnClickListener {
            val intent = Intent(this, questionActivity_Computing::class.java)
            startActivity(intent)
            finish()
        }
        engineering.setOnClickListener {
            val intent = Intent(this, questionActivity_Engineering::class.java)
            startActivity(intent)
            finish()
        }
        hospitality.setOnClickListener {
            val intent = Intent(this, questionActivity_Hospitality::class.java)
            startActivity(intent)
            finish()
        }
        humanities.setOnClickListener {
            val intent = Intent(this, questionActivity_Humanities::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAndDisableButton(scoreKey: String, button: ConstraintLayout) {
        val sharedPreferences = getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)
        val score = sharedPreferences.getInt(scoreKey, 0)
        if (score != 0) {
            button.isEnabled = false
//            button.setBackgroundColor(Color.GRAY)
            button.setBackgroundResource(R.drawable.button_disable)
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
            submit.visibility = View.VISIBLE
        } else {
            submit.isEnabled = false
            submit.visibility = View.INVISIBLE
        }
    }
}
