package com.example.trigonometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_trigonometry_ratios_and_funtions.*

class TrigonometryRatiosAndFuntions : AppCompatActivity() {
    val quizName = "Trigonometry Ratios and Functions"
    val currQuizScore = 0
    val maxQuizScore = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigonometry_ratios_and_funtions)

        pdf_view_ratios_functions.fromAsset("basic_formulas.pdf").load()

        ratios_and_functions_quiz.setOnClickListener {
            val intent = Intent(this, ConfirmQuizActivity::class.java)
            intent.putExtra("QuizInfo",QuizInfoClass(quizName = quizName, currQuizScore = currQuizScore, maxQuizScore = maxQuizScore))
            startActivity(intent)
        }
    }
}
