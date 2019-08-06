package com.example.trigopdfsclasses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trigonometry.ConfirmQuizActivity
import com.example.trigonometry.QuizInfoClass
import com.example.trigonometry.R
import kotlinx.android.synthetic.main.activity_trigonometric_rations_values.*

class TrigonometricRatiosAndValues : AppCompatActivity() {

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
