package com.example.trigonometry

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class QuizActivity : AppCompatActivity() {
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
        val quizInfoClass = ConfirmQuizActivity.quizName
        Log.wtf("NullPointerIdiot", "$quizInfoClass")
        quiz = Quiz(quizInfoClass)
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
                questionsArray.add(Questions("Value of tan(0)", "1", "0", "-1", "-1",2))
                questionsArray.add(Questions("Value of cosec(90)", "1", "not defined", "-1", "-1",1))
                questionsArray.add(Questions("Value of sec(30)", "1/2", "0", "2/√3", "-1",1))
            }
            "Basic Trigonometric Formulas" -> {
                questionsArray.add(Questions("sin(x) * cosec(x) =", "0", "1","-1","1/2",2))
                questionsArray.add(Questions("cos(x) * sec(x)","1", "infinity","0","2",1))
                questionsArray.add(Questions("tan(x) * cot(x)","1", "infinity","0","2",1))
                questionsArray.add(Questions("tan(x) = ","cos(x)/sin(x)","sec(x)/cosec(x)", "sec(x)/cos(x)","cosec(x)/sin(x)",2))

                questionsArray.add(Questions("sin^2(x) + cos^2(x) = ","0","2","3","1",4))
                questionsArray.add(Questions("1+tan^2(x) = ","cosec^2(x)","sin^2(x)","sec^2(x)","cot^2(x)",3))

                questionsArray.add(Questions("1+cot^2(x) = ","cosec^2(x)","sin^2(x)","sec^2(x)","cot^2(x)",1))
            }
            "Trigonometric ratios and functions" -> {
                questionsArray.add(Questions("sin(x)","p/h","p/b","h/b","b/h",1))
                questionsArray.add(Questions("cos(x)","p/h","p/b","h/b","b/h",4))
                questionsArray.add(Questions("tan(x)","p/h","p/b","h/b","b/h",2))
                questionsArray.add(Questions("cosec(x)","p/h","p/b","h/p","b/h",3))
                questionsArray.add(Questions("sec(x)","p/h","p/b","h/b","b/h",3))
                questionsArray.add(Questions("cot(x)","b/p","p/b","h/b","b/h",1))
            }
            "Double Triple Half" -> {

                questionsArray.add(Questions("sin(2A) = ","2sin(A)sin(A)","2cos(A)sin(A)","2cos(A)cos(A)","2sin(A)cosec(A)",2))
                questionsArray.add(Questions("cos(2A) = ","sin^2(A) - cos^2(A)","1+cos^2(A)","cos^2(A) - sin^2(A)","1+2sin^2(A)",3))
                questionsArray.add(Questions("tan(2A) = ", "2tan(A)/(1-tan^2(A)", "2tan(A)/(1+tan^2(A)", "tan(A)/(1-tan^2(A)", "tan(A)/(1+tan^2(A)",1))
                questionsArray.add(Questions("cot(2A) = ", "(1-cot^2(A))/2cot(A)", "(1+cot^2(A))/2cot(A)", "(1-sin^2(A))/2cot(A)", "(-1+cot^2(A))/2cot(A)",4))
                questionsArray.add(Questions("sin(A) = ", "2sin(A)cos(A)", "2sin(A/2)cos(A/2)", "sin(A/2)cos(A/2)", "2sin(A/2)cosec(A/2)",2))
                questionsArray.add(Questions("sin(3A) = ", "3sin(A) - 4 sin^3(A)", "4sin(A) - 3sin^3(A)", "3sin(A) + 4 sin^3(A)", "4sin(A) + 3 sin^3(A)",1))
                questionsArray.add(Questions("cos(3A) = ", "4cos^3 + 3cos(A)", "3cos - 4cos^3(A)", "4cos^3 - 3cos(A)", "4cos^3 - 3sin(A)",3))
                questionsArray.add(Questions("tan(3A) = ", "(tan(A) - 3tan^3(A))/(1-tan^2(A)", "(3tan(A) + tan^3(A))/(1-3tan^2(A)", "(3tan(A) - tan^3(A))/(1+3tan^2(A)", "(3tan(A) - tan^3(A))/(1-3tan^2(A)", 4))

            }
            "Transformation" -> {
                questionsArray.add(Questions("2sin(A)cos(B) = ","sin(A+B) - sin(A-B)","sin(A-B) + sin(A-B)","sin(A+B) + sin(A+B)","sin(A+B) + sin(A-B)",4))
                questionsArray.add(Questions("2cos(A)sin(B) = ", "sin(A+B) - sin(A-B)", "sin(A+B) + sin(A-B)", "sin(A-B) + sin(A-B)", "sin(A+B) + sin(A+B)", 1))
                questionsArray.add(Questions("2cos(A)cos(B) = ", "sin(A+B) + cos(A-B)", "cos(A+B) + cos(A-B)", "sin(A+B) - cos(A-B)", "cos(A+B) - cos(A-B)",2))
                questionsArray.add(Questions("2sin(A)sin(B) = ", "sin(A+B) - sin(A-B)", "sin(A+B) - cos(A-B)", "cos(A+B) - cos(A-B)", "cos(A+B) + cos(A-B)",3))

                questionsArray.add(Questions("sin(A) + sin(B) = ","2cos((A+B)/2)sin((A-B)/2)","2sin((A-B)/2)cos((A-B)/2)","2sin((A+B)/2)cos((A+B)/2)","2sin((A+B)/2)cos((A-B)/2)",4))
                questionsArray.add(Questions("sin(A) - sin(B) = ","2cos((A+B)/2)sin((A-B)/2)","2sin((A-B)/2)cos((A-B)/2)","2sin((A+B)/2)cos((A+B)/2)","2sin((A+B)/2)cos((A-B)/2)",1))
                questionsArray.add(Questions("cos(A) + cos(B) = ","2cos((A+B)/2)sin((A-B)/2)","2sin((A-B)/2)cos((A-B)/2)","2cos((A+B)/2)cos((A-B)/2)","2sin((A+B)/2)cos((A-B)/2)",3))
                questionsArray.add(Questions("cos(A) - cos(B) = ","2cos((A+B)/2)sin((A-B)/2)","2sin((A+B)/2)sin((A-B)/2)","2sin((A+B)/2)cos((A+B)/2)","cos((A+B)/2)cos((A-B)/2)",2))
            }
            "Conditional Identities" -> {
                //Ishank
                
            }
            "Trigonometric Equations and general values" -> {
                //Ishank
                questionsArray.add(Questions("If 0 <t< 2π such that sin(t) = √2/2 and cot(t)<0,then t=?","π/4","3π/4","5π/4","7π/4",2))
                questionsArray.add(Questions("If 0 <t< 2π and sin(t) = -1, then t=","π/2","3π/2","5π/4","π",2))
                questionsArray.add(Questions("If 0 <α< π and cos(α) = -√3/2, then α =","π/6","-π/6","5π/6","7π/6",3))
                questionsArray.add(Questions("If -2π <t< 0 and sin(t) = -1/2, then t =","-5π/6","-7π/6","-5π/4","-5π/3",1))
                questionsArray.add(Questions("Find all angles θ such that -2π <θ< 2π and cos(θ) = √2/2","{-7π/4, -π/4 , π/4 , 7π/4}","{-π/4, -3π/4 , π/4 , 3π/4}","{-5π/4, π/4 , 3π/4}","{π/4, 3π/4}",1))
                questionsArray.add(Questions("Find all values of t such that sin(t) = 0","t = kπ/2 ; k = 0","t = kπ/4 ; k = 0","t = kπ ; k = 0","t = 2kπ ; k = 0",3))
                questionsArray.add(Questions("Find all values of t such that cos(πt) = 1","t = 2kπ ; k = 0","t = 2k ; k = 0","t = k ; k = 0","t = kπ ; k = 0",2))
            }
            "Addition and Subtraction" ->{
                //Ishank
            }

        }
    }
}

data class Questions (val ques : String, val op1 : String, val op2 : String, val op3 : String,val op4 : String, val ans : Int)

