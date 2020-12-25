package com.trigonometryapp.trigopdfsclasses

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trigonometryapp.trigonometry.ConfirmQuizActivity
import com.trigonometryapp.trigonometry.QuizInfoClass
import com.trigonometryapp.trigonometry.R
import kotlinx.android.synthetic.main.activity_trigonometry_ratios_and_funtions.*

class TrigoEquationsAndGeneralValues : AppCompatActivity() {
    val currScore = 0
    val quizName = "Trigonometric Equations and general values"
    val maxScore = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigonometry_ratios_and_funtions)
        pdf_view_ratios_functions.fromAsset("Trigonometric _equations_and_general_values.pdf")
            .load()
        textView2.text = textView2.text.toString() + getData(quizName) + "/" + maxScore

        ratios_and_functions_quiz.setOnClickListener {
            val intent = Intent(this, ConfirmQuizActivity::class.java)
            val quizIntent = QuizInfoClass(quizName, currScore, maxScore)
            intent.putExtra("QuizInfo", quizIntent)
            startActivity(intent)
        }
    }

    private fun getData(quiz: String): Int {
        val sharedPreferences = getSharedPreferences("testScores", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(quiz, 0)
    }
}