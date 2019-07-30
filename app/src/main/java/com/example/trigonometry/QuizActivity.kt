package com.example.trigonometry

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Parcelable
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.android.synthetic.main.quiz_activity_v2.*
import kotlinx.android.synthetic.main.quiz_activity_v2.*
import java.lang.Thread.sleep
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class QuizActivity : AppCompatActivity() {

    //companion object{
       // var quiz : Quiz? = null
  //  }


    var timeLeftInMillis = 7000L
    var countDownTimer : CountDownTimer? = null
    var currentQuestion = 0
    var usersAnswers = ArrayList<Int>(4)

    init {
        //val quizInfoClass = intent.getParcelableExtra<QuizInfoClass>("QuizStart")
     //   quiz = Quiz(quizName = "Trigonometric Ratio's values")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_activity_v2)

       // startQuiz(quiz!!.questionsArray)
    }
/*


    fun startQuiz(quiz : ArrayList<Questions>){

        var index = 0
        var i = quiz[0]

            question_display_quiz.text = i.ques
            option_1.text = i.op1
            option_2.text = i.op2
            option_3.text = i.op3

            //question_no_view.text = "Question No: "+(index+1).toString()

            timeLeftInMillis = 7000
          //  startCountDown(quiz)

        /**next_question_button.setOnClickListener {
            storeAnswer()
            currentQuestion++

            if (currentQuestion == quiz.size){
                startActivity(Intent(this,ShowResult::class.java).putExtra("resultOfQuiz",usersAnswers))
                finish()
            }
            countDownTimer?.cancel()
            nextQuestion(currentQuestion, quiz)

        }
        */
    }

    fun nextQuestion(currentQuestion : Int, quiz : ArrayList<Questions>){

        var index = currentQuestion
        Log.wtf("really wtf","$index")
        if  (index == quiz.size){
            return
        }
        var i = quiz[index]

        question_display_quiz.text = i.ques
        option_1.text = i.op1
        option_2.text = i.op2
        option_3.text = i.op3

       // question_no_view.text = "Question No: "+(index+1).toString()

        //startCountDown(quiz)
    }


    /**

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
    */


    fun startCountDown(){
        countDownTimer = object : CountDownTimer(10000,1000){
            override fun onFinish() {
                timer_view_quiz.text = "0"
                progress_bar_quiz.progress = 0
            }

            override fun onTick(millisUntilFinished: Long) {

                if(millisUntilFinished <= 3000){
                    progress_bar_quiz.setBackgroundColor(Color.RED)
                }

                timeLeftInMillis = millisUntilFinished
                val textToSet = (timeLeftInMillis/1000).toInt()
                timer_view_quiz.text = textToSet.toString()

                var progress = timeLeftInMillis/100
                progress_bar_quiz.progress = progress.toInt()
            }

        }.start()
    }


*/

}



