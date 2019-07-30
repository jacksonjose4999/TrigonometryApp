package com.example.trigonometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_confirm_quiz.*

class ConfirmQuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_quiz)

        val quizInfo = intent.getParcelableExtra<QuizInfoClass>("QuizInfo")

        quiz_topic_view.text = quizInfo.quizName
        current_score_view.text = "Current Score : " + quizInfo.currQuizScore.toString()
        max_score_view.text = "Maximum Score Possible : " + quizInfo.maxQuizScore.toString()

        start_quiz_button.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("QuizStart", quizInfo)
            startActivity(intent)
        }
    }
}
