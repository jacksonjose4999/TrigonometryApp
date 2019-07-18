package com.example.trigonometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Parcelable
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.Thread.sleep
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class QuizActivity : AppCompatActivity() {

    companion object{
        var quiz : Quiz? = null
    }


    var timeLeftInMillis = 7000L
    var countDownTimer : CountDownTimer? = null
    var currentQuestion = 0
    var usersAnswers = ArrayList<Int>(4)

    init {
        //val quizInfoClass = intent.getParcelableExtra<QuizInfoClass>("QuizStart")
        quiz = Quiz(quizName = "Trigonometric Ratio's values")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        startQuiz(quiz!!.questionsArray)
    }



    fun startQuiz(quiz : ArrayList<Questions>){

        var index = 0
        var i = quiz[0]

            question_view.text = i.ques
            option_1.text = i.op1
            option_2.text = i.op2
            option_3.text = i.op3

            question_no_view.text = "Question No: "+(index+1).toString()

            timeLeftInMillis = 7000
            startCountDown(quiz)

        next_question_button.setOnClickListener {
            storeAnswer()
            currentQuestion++

            if (currentQuestion == quiz.size){
                startActivity(Intent(this,ShowResult::class.java).putExtra("resultOfQuiz",usersAnswers))
                finish()
            }
            countDownTimer?.cancel()
            nextQuestion(currentQuestion, quiz)

        }
    }

    fun nextQuestion(currentQuestion : Int, quiz : ArrayList<Questions>){

        var index = currentQuestion
        Log.wtf("really wtf","$index")
        if  (index == quiz.size){
            displayResult()
            return
        }
        var i = quiz[index]

        question_view.text = i.ques
        option_1.text = i.op1
        option_2.text = i.op2
        option_3.text = i.op3

        question_no_view.text = "Question No: "+(index+1).toString()

        startCountDown(quiz)
    }

    fun displayResult(){
        for (i in usersAnswers){
            Log.wtf("resultttt", "$i")
        }

    }

    fun startCountDown(quiz: ArrayList<Questions>){
        timeLeftInMillis = 7000
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                storeAnswer()
                timeLeftInMillis = 0
                updateCountDownText()
                currentQuestion++
                if (currentQuestion>=quiz.size){
                    displayResult()
                    return
                }
                nextQuestion(currentQuestion, quiz)
            }
        }.start()
    }



    fun storeAnswer(){
        val selectedButton = radioGroup.checkedRadioButtonId
        if(option_1.id == selectedButton){
            usersAnswers.add(1)
        }
        else if (option_2.id == selectedButton){
            usersAnswers.add(2)
        }
        else if (option_3.id == selectedButton){
            usersAnswers.add(3)
        }

        else{
            usersAnswers.add(0)
        }
    }



    fun updateCountDownText(){
        var seconds = timeLeftInMillis/1000
        timer_view.text = "00:0"+seconds
    }
}



class Quiz (val quizName: String){
    var questionsArray = ArrayList<Questions>()

    init {
        when(quizName) {
            "Trigonometric Ratio's values" -> {
                questionsArray.add(Questions("sin(0)", "1", "0", "-1", 2))
                questionsArray.add(Questions("cos(0)", "1", "0", "-1", 1))
                questionsArray.add(Questions("tan(90)", "1", "not defined", "-1", 2))
                questionsArray.add(Questions("sin(30)", "1/2", "0", "1", 1))
            }
        }
    }
}


class Questions (val ques : String, val op1 : String, val op2 : String, val op3 : String, val ans : Int)

