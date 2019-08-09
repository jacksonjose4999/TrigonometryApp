package com.example.trigopdfsclasses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trigonometry.ConfirmQuizActivity
import com.example.trigonometry.QuizInfoClass
import com.example.trigonometry.R
import kotlinx.android.synthetic.main.activity_trigonometric_rations_values.*
import kotlinx.android.synthetic.main.activity_trigonometry_ratios_and_funtions.*

class TrigoEquationsAndGeneralValues : AppCompatActivity() {
    val currScore = 0
    val quizName = "Trigonometric Equations and general values"
    val maxScore = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigonometry_ratios_and_funtions)
        pdf_view_ratios_functions.fromAsset("Trigonometric _equations_and_general_values.pdf").load()

        ratios_and_functions_quiz.setOnClickListener {
            val intent = Intent(this, ConfirmQuizActivity::class.java)
            val quizIntent = QuizInfoClass("Trigonometric ratios and functions",currScore,maxScore)
            intent.putExtra("QuizInfo",quizIntent)
            startActivity(intent)
        }
    }
}