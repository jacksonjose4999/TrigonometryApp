package com.example.trigonometry

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_confirm_quiz.*
import kotlinx.android.synthetic.main.toolbar.*

class ConfirmQuizActivity : AppCompatActivity() {
    companion object{
        var quizName = ""
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_quiz)


        val quizInfo = intent.getParcelableExtra<QuizInfoClass>("QuizInfo")

        if(quizInfo.quizName == "Conditional Identities")
            quizInfo.currQuizScore = 12
        else
            quizInfo.currQuizScore = 0

        quiz_topic_view.text = quizInfo.quizName
        current_score_view.text = "Current Score : " + quizInfo.currQuizScore.toString()
        max_score_view.text = "Maximum Score Possible : " + quizInfo.maxQuizScore.toString()
        start_quiz_button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            //val quizInfo = intent.getParcelableExtra<QuizInfoClass>("QuizInfo")
            val string = quizInfo.quizName
            intent.putExtra("QuizAct", string)
            Log.wtf("NullPointerIdiot", "value putted ${string}")
            quizName = string
            startActivity(intent)
        }
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
