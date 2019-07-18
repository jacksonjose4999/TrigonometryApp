package com.example.trigonometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_trigonometric_rations_values.*

class TrigonometricRationsValues : AppCompatActivity() {

    val currScore = 0
    val quizName = "Trigonometric Ratio's values"
    val maxScore = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigonometric_rations_values)

        values_quiz.setOnClickListener {
            val intent = Intent(this, ConfirmQuizActivity::class.java)
            val quizInfo = QuizInfoClass(quizName, currScore, maxScore)
            intent.putExtra("QuizInfo",quizInfo)
            startActivity(intent)
        }
    }
}

