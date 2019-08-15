package com.trigonometryapp.trigonometry

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_confirm_quiz.*

class ConfirmQuizActivity : AppCompatActivity() {
    companion object{
        var quizName = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_quiz)
        val quizInfo = intent.getParcelableExtra<QuizInfoClass>("QuizInfo")

        quiz_topic_view.text = quizInfo.quizName
        current_score_view.text = "Current Score : " + quizInfo.currQuizScore.toString()+getData(quizInfo.quizName)
        max_score_view.text = "Maximum Score Possible : " + quizInfo.maxQuizScore.toString()
        start_quiz_button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            val string = quizInfo.quizName
            intent.putExtra("QuizAct", string)
            quizName = string
            startActivity(intent)
            finish()
        }
    }

    private fun getData(quiz: String): Int {
        val sharedPreferences = getSharedPreferences("testScores", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(quiz, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
