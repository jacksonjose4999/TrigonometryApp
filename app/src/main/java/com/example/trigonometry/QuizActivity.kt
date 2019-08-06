package com.example.trigonometry

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    val timeInMillis = 11000L
    var remainingtime: Long? = null
    var index = 0
    var userAnwers = ArrayList<Int>()
    var countDownTimer : CountDownTimer? = null
    var option1Selected = false
    var option2Selected = false
    var option3Selected = false
    var option4Selected = false


    companion object{
        var quiz: Quiz? = null
    }

    init {
        quiz = Quiz("Trigonometric Ratio's values")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setOnClickListeners()

        startQuiz(quiz!!.questionsArray)
    }

    private fun startQuiz(quiz : ArrayList<Questions>){

        loadQuestion(quiz)

        question_next_button.setOnClickListener {
            storeAnswer()
            countDownTimer?.cancel()
            loadQuestion(quiz)
        }
    }

    fun setOnClickListeners(){
        option_1.setOnClickListener {
            option_1.background = getDrawable(R.drawable.selected_option_button_style)
            option1Selected = true
            setOtherButtonsToNormal(1)
        }

        option_2.setOnClickListener {
            option_2.background = getDrawable(R.drawable.selected_option_button_style)
            option2Selected = true
            setOtherButtonsToNormal(2)
        }

        option_3.setOnClickListener {
            option_3.background = getDrawable(R.drawable.selected_option_button_style)
            option3Selected = true
            setOtherButtonsToNormal(3)
        }

        option_4.setOnClickListener {
            option_4.background = getDrawable(R.drawable.selected_option_button_style)
            option4Selected = true
            setOtherButtonsToNormal(4)
        }
    }


    fun setOtherButtonsToNormal( option_no : Int) {
        when (option_no) {
            1 -> {
                option_2.background = getDrawable(R.drawable.options_button_style)
                option_3.background = getDrawable(R.drawable.options_button_style)
                option_4.background = getDrawable(R.drawable.options_button_style)
            }
            2 -> {
                option_1.background = getDrawable(R.drawable.options_button_style)
                option_3.background = getDrawable(R.drawable.options_button_style)
                option_4.background = getDrawable(R.drawable.options_button_style)
            }
            3 -> {
                option_2.background = getDrawable(R.drawable.options_button_style)
                option_1.background = getDrawable(R.drawable.options_button_style)
                option_4.background = getDrawable(R.drawable.options_button_style)
            }
            4 -> {
                option_2.background = getDrawable(R.drawable.options_button_style)
                option_3.background = getDrawable(R.drawable.options_button_style)
                option_1.background = getDrawable(R.drawable.options_button_style)
            }
        }
    }

    fun setAllToFalse(option : Int){
        if (option != 1)
            option1Selected = false
        if (option != 2)
            option2Selected = false
        if (option != 3)
            option3Selected = false
        if (option != 4)
            option4Selected = false
    }

    private fun loadQuestion(quiz : ArrayList<Questions>){
        setOtherButtonsToNormal(1)
        setOtherButtonsToNormal(2)
        if  (index == quiz.size){
            storeAnswer()
            displayResult()
            Log.wtf("TAGGG","index is $index and quiz size is ${quiz.size}")
            return
        }
        setAllToFalse(0)

        var questions = quiz[index]
        question_no_view.text = "Question No: "+(index+1)
        question_display_quiz.text = questions.ques
        option_1.text = questions.op1
        option_2.text = questions.op2
        option_3.text = questions.op3
        option_4.text = questions.op4

        startCountDown(quiz)
        index++
    }

    private fun startCountDown(quiz: ArrayList<Questions>){
        remainingtime = timeInMillis

        countDownTimer = object : CountDownTimer(remainingtime!!, 1000){
            override fun onFinish() {
                timer_view_quiz.text = "0"
                progress_bar_quiz.progress = 0

                storeAnswer()
                loadQuestion(quiz)
            }

            override fun onTick(millisUntilFinished: Long) {
                remainingtime = millisUntilFinished
                val textToSet = (remainingtime!!/1000).toInt()
                timer_view_quiz.text = textToSet.toString()

                var progress = remainingtime!!/100
                progress_bar_quiz.progress = progress.toInt()
            }

        }.start()
    }

    private fun displayResult(){
        var k = 0
        for (i in userAnwers){
            Log.wtf("AllAnswers","index $k, value $i")
            k++
        }
        userAnwers.removeAt(userAnwers.size-1)

        k = 0
        for (i in userAnwers){
            Log.wtf("AllAnswers","index $k, value $i")
            k++
        }
        startActivity(Intent(this, ShowResult::class.java).putIntegerArrayListExtra("resultOfQuiz",userAnwers))
    }

    private fun storeAnswer(){
        when {
            option1Selected -> {
                userAnwers.add(1)
                Log.wtf("Store Answer", "the answer stored is 1")
            }
            option2Selected -> {
                userAnwers.add(2)
                Log.wtf("Store Answer", "the answer stored is 2")

            }

            option3Selected -> {
                Log.wtf("Store Answer", "the answer stored is 3")
                userAnwers.add(3)
            }
            option4Selected -> {
                Log.wtf("Store Answer", "the answer stored is 4")
                userAnwers.add(4)
            }
            else -> {
                Log.wtf("Store Answer", "the answer stored is 5")
                userAnwers.add(0)
            }
        }
    }
}




class Quiz (quizName: String){
    var questionsArray = ArrayList<Questions>()

    init {
        when(quizName) {
            "Trigonometric Ratio's values" -> {
                questionsArray.add(Questions("Value of sin(0)", "1", "0", "-1", "-1",2))
                questionsArray.add(Questions("Value of cos(0)", "1", "0", "-1", "-1",1))
                questionsArray.add(Questions("Value of tan(90)", "1", "not defined", "-1", "-1",2))
                questionsArray.add(Questions("Value of sin(30)", "1/2", "0", "1", "-1",1))
                questionsArray.add(Questions("Value of cot(0)", "not defined", "0", "-1", "-1",1))
                questionsArray.add(Questions("Value of tan(0)", "1", "0", "-1", "-1",1))
                questionsArray.add(Questions("Value of cosec(90)", "1", "not defined", "-1", "-1",2))
                questionsArray.add(Questions("Value of sec(30)", "1/2", "0", "1", "-1",1))
            }

        }
    }
}

data class Questions (val ques : String, val op1 : String, val op2 : String, val op3 : String,val op4 : String, val ans : Int)

