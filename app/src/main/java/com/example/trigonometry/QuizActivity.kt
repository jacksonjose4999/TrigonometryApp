package com.example.trigonometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.Toast
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
        var quizQ: Quiz? = null
        var noOfQuestions : Int? = null
    }

    init {
        val quizInfoClass = ConfirmQuizActivity.quizName
        Log.wtf("NullPointerIdiot", "$quizInfoClass")
        quizQ = Quiz(quizInfoClass)
        noOfQuestions = quizQ!!.questionsArray.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setOnClickListeners()
        quizback.setBackgroundResource(R.drawable.cropx)

        startQuiz(quizQ!!.questionsArray)
    }

    private fun startQuiz(quiz : ArrayList<Questions>){

        loadQuestion(quiz)
        question_next_button.setOnClickListener {
            storeAnswer()
            countDownTimer?.cancel()
            loadQuestion(quiz)
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit Quiz", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
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
    var justClicked = false


    private fun loadQuestion(quiz : ArrayList<Questions>){
        setOtherButtonsToNormal(1)
        setOtherButtonsToNormal(2)
        if  (index == quiz.size){
            if (justClicked){
                return
            }
            justClicked = true
            storeAnswer()
            displayResult()
            Log.wtf("TAGGG","index is $index and quizQ size is ${quiz.size}")
            return
        }
        setAllToFalse(0)


            var questions = quiz[index]
            question_no_view.text = "Question No: " + (index + 1) + "/$noOfQuestions"
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
        finish()
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
    var quizName: String? = null
    init {
        this.quizName = quizName
        when(quizName) {
            "Trigonometric Ratio's values" -> {
                questionsArray.add(Questions("Value of sin(0)", "1", "0", "-1", "1/2",2))
                questionsArray.add(Questions("Value of cos(0)", "1", "0", "-1", "-1/2",1))
                questionsArray.add(Questions("Value of tan(90)", "1", "not defined", "-1", "-1/2",2))
                questionsArray.add(Questions("Value of sin(30)", "1/2", "0", "1", "-1",1))
                questionsArray.add(Questions("Value of cot(0)", "not defined", "0", "-1", "-1/2",1))
                questionsArray.add(Questions("Value of tan(0)", "1", "0", "-1", "-1/2",2))
                questionsArray.add(Questions("Value of cosec(90)", "1", "not defined", "-1", "-1/2",1))
                questionsArray.add(Questions("Value of sec(30)", "1/2", "0", "2/√3", "-1",3))
            }
            "Basic Trigonometric Formulas" -> {
                questionsArray.add(Questions("sin(x) × cosec(x) =", "0", "1","-1","1/2",2))
                questionsArray.add(Questions("cos(x) × sec(x)","1", "infinity","0","2",1))
                questionsArray.add(Questions("tan(x) × cot(x)","1", "infinity","0","2",1))
                questionsArray.add(Questions("tan(x) = ","cos(x)/sin(x)","sec(x)/cosec(x)", "sec(x)/cos(x)","cosec(x)/sin(x)",2))
                questionsArray.add(Questions("sin²(x) + cos²(x) = ","0","2","3","1",4))
                questionsArray.add(Questions("1+tan²(x) = ","cosec²(x)","sin²(x)","sec²(x)","cot²(x)",3))
                questionsArray.add(Questions("1+cot²(x) = ","cosec²(x)","sin²(x)","sec²(x)","cot²(x)",1))
            }
            "Trigonometric Ratio's and Functions" -> {
                questionsArray.add(Questions("sin(x)","p/h","p/b","h/b","b/h",1))
                questionsArray.add(Questions("cos(x)","p/h","p/b","h/b","b/h",4))
                questionsArray.add(Questions("tan(x)","p/h","p/b","h/b","b/h",2))
                questionsArray.add(Questions("cosec(x)","p/h","p/b","h/p","b/h",3))
                questionsArray.add(Questions("sec(x)","p/h","p/b","h/b","b/h",3))
                questionsArray.add(Questions("cot(x)","b/p","p/b","h/b","b/h",1))
            }
            "Double Triple Half" -> {
                questionsArray.add(Questions("sin(2A) = ","2sin(A)sin(A)","2cos(A)sin(A)","2cos(A)cos(A)","2sin(A)cosec(A)",2))
                questionsArray.add(Questions("cos(2A) = ","sin²(A) - cos²(A)","1+cos²(A)","cos²(A) - sin²(A)","1+2sin²(A)",3))
                questionsArray.add(Questions("tan(2A) = ", "2tan(A)/(1-tan²(A)", "2tan(A)/(1+tan²(A)", "tan(A)/(1-tan²(A)", "tan(A)/(1+tan²(A)",1))
                questionsArray.add(Questions("cot(2A) = ", "(1-cot²(A))/2cot(A)", "(1+cot²(A))/2cot(A)", "(1-sin²(A))/2cot(A)", "(-1+cot²(A))/2cot(A)",4))
                questionsArray.add(Questions("sin(A) = ", "2sin(A)cos(A)", "2sin(A/2)cos(A/2)", "sin(A/2)cos(A/2)", "2sin(A/2)cosec(A/2)",2))
                questionsArray.add(Questions("sin(3A) = ", "3sin(A) - 4 sin^3(A)", "4sin(A) - 3sin^3(A)", "3sin(A) + 4 sin^3(A)", "4sin(A) + 3 sin^3(A)",1))
                questionsArray.add(Questions("cos(3A) = ", "4cos^3 + 3cos(A)", "3cos - 4cos^3(A)", "4cos^3 - 3cos(A)", "4cos^3 - 3sin(A)",3))
                questionsArray.add(Questions("tan(3A) = ", "(tan(A) - 3tan^3(A))/(1-tan²(A)", "(3tan(A) + tan^3(A))/(1-3tan²(A)", "(3tan(A) - tan^3(A))/(1+3tan²(A)", "(3tan(A) - tan^3(A))/(1-3tan²(A)", 4))
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
                questionsArray.add(Questions("sin(A)+sin(B)+sin(C) =  ","4cos(A/2)cos(B/2)cos(C/2)","2cos(A/2)cos(B/2)cos(C/2)","1+4cos(A/2)cos(B/2)cos(C/2)","1-4cos(A/2)cos(B/2)cos(C/2)",1))
                questionsArray.add(Questions("cos(A)+cos(B)+cos(C) = ","4sin(A/2)sin(B/2)sin(C/2)","2sin(A/2)sin(B/2)sin(C/2)","1+4sinA/2)sin(B/2)sin(C/2)","1-4sin(A/2)sin(B/2)sin(C/2)",3))
                questionsArray.add(Questions("sin(2A)+sin(2B)+sin(2C) =  ","4cos(A)cos(B)cos(C)","4sin(A)sin(B)sin(C)","1+4cos(A)cos(B)cos(C)","1-4sin(A)sin(B)sin(C)",2))
                questionsArray.add(Questions("cos(2A)+cos(2B)+cos(2C) =  ","4cos(A)cos(B)cos(C)","4sin(A)sin(B)sin(C)","1-4cos(A)cos(B)cos(C)","1-4sin(A)sin(B)sin(C)",3))
                questionsArray.add(Questions("cos(2A)+cos(2B)-cos(2C) =  ","1-4cos(A)cos(B)sin(C)","1-4sin(A)sin(B)cos(C)","1+4cos(A)cos(B)sin(C)","1+4sin(A)sin(B)cos(C)",2))
                questionsArray.add(Questions("cos²(A)+cos²(B)+cos²(C) =  ","1+2cos(A)cos(B)cos(C)","1-2cos(A)cos(B)cos(C)","1+4cos(A)cos(B)cos(C)","1-4cos(A)cos(B)cos(C)",2))
                questionsArray.add(Questions("sin²(A)+sin²(B)+sin²(C) =  ","2cos(A)cos(B)cos(C)","2sin(A)sin(B)cos(C)","2sin(A)sin(B)sin(C)","none of the above",4))
                questionsArray.add(Questions("sin²(A/2)+sin²(B/2)+sin²(C/2) =  ","1-4cos(A/2)cos(B/2)cos(C/2)","1+2sin(A/2)sin(B/2)cos(C/2)","1-2sin(A/2)sin(B/2)cos(C/2)","1-2cos(A/2)cos(B/2)cos(C/2)",3))
                questionsArray.add(Questions("cos²(A/2)+cos²(B/2)+cos²(C/2) =  ","2cos(A/2)cos(B/2)sin(C/2)","4cos(A/2)cos(B/2)sin(C/2)","1+2cos(A/2)cos(B/2)sin(C/2)","1-2cos(A/2)cos(B/2)sin(C/2)",1))
            }

            "Trigonometric Equations and general values" -> {
                questionsArray.add(Questions("sin(x) = sin(θ), then what is x?","x = (n+1)π + (-1)^n(θ)","x = nπ + (-1)^n(θ)","x = nπ - (-1)^n(θ)","x = nπ + (1)^n(θ)",2))
                questionsArray.add(Questions("cos(x) = cos(θ), then what is x?","nπ +- θ","2nπ +- θ","nπ +- 2θ","4nπ +- θ",2))
                questionsArray.add(Questions("tan(x) = tan(θ), then x =","nπ - θ","nπ + 2θ","nπ + θ","2nπ + θ",3))
                questionsArray.add(Questions("cosec(x) = cosec(θ), then x =","x = (n+1)π + (-1)^n(θ)","x = nπ + (-1)^n(θ)","x = nπ - (-1)^n(θ)","x = nπ + (1)^n(θ)",2))
                questionsArray.add(Questions("sec(x) = sec(θ), then what is x?","nπ +- θ","2nπ +- θ","nπ +- 2θ","4nπ +- θ",2))

                questionsArray.add(Questions("sin^2(x) = sin^2(θ), then what is x?", "2nπ +- θ","nπ - θ","nπ + θ","nπ +- θ",4))
                questionsArray.add(Questions("cos^2(x) = cos^2(θ), then what is x?", "2nπ +- θ","nπ - θ","nπ + θ","nπ +- θ",4))
                questionsArray.add(Questions("tan^2(x) = tan^2(θ), then what is x?", "2nπ +- θ","nπ - θ","nπ + θ","nπ +- θ",4))
            }

            "Addition and Subtraction" ->{
                questionsArray.add(Questions("sin(A+B) = ","sin(A)cos(B) - cos(A)sin(B)","sin(A)cos(B) + cos(A)sin(B)","sin(A)sin(B) + cos(A)cos(B)","sin(A)sin(B) - cos(A)cos(B)",2))
                questionsArray.add(Questions("cos(A+B) = ","cos(A)cos(B) - sin(A)sin(B)","sin(A)cos(B) + cos(A)sin(B)","cos(A)cos(B) + sin(A)sin(B)","sin(A)cos(B) - cos(A)sin(B)",1))
                questionsArray.add(Questions("sin(A-B) = ","sin(A)cos(B) + cos(A)sin(B)","sin(A)cos(B) - cos(A)sin(B)","sin(A)sin(B) - cos(A)cos(B)","sin(A)sin(B) + cos(A)cos(B)",2))
                questionsArray.add(Questions("cos(A-B) = ","cos(A)cos(B) - sin(A)sin(B)","sin(A)cos(B) + cos(A)sin(B)","cos(A)cos(B) + sin(A)sin(B)","sin(A)cos(B) - cos(A)sin(B)",3))
                questionsArray.add(Questions("tan(A+B) = ","tan(A)+tan(B)/1+tan(A)tan(B)","tan(A)-tan(B)/1+tan(A)tan(B)","tan(A)-tan(B)/1-tan(A)tan(B)","tan(A)+tan(B)/1-tan(A)tan(B)",4))
                questionsArray.add(Questions("tan(A-B) = ","tan(A)+tan(B)/1+tan(A)tan(B)","tan(A)-tan(B)/1+tan(A)tan(B)","tan(A)-tan(B)/1-tan(A)tan(B)","tan(A)+tan(B)/1-tan(A)tan(B)",2))
                questionsArray.add(Questions("cot(A+B) = ","cot(B)cot(A)-1/cot(B)+cot(A)","cot(B)cot(A)-1/cot(B)-cot(A)","cot(B)cot(A)+1/cot(B)+cot(A)","cot(B)cot(A)+1/cot(B)-cot(A)",1))
                questionsArray.add(Questions("cot(A-B) = ","cot(B)cot(A)-1/cot(B)+cot(A)","cot(B)cot(A)-1/cot(B)-cot(A)","cot(B)cot(A)+1/cot(B)+cot(A)","cot(B)cot(A)+1/cot(B)-cot(A)",2))
                questionsArray.add(Questions("sin(A+B+C) = ","sin(A)cos(B)cos(C) + cos(A)sin(B)cos(C) - cos(A)cos(B)sin(C) - sin(A)sin(B)sin(C)","sin(A)cos(B)cos(C) - cos(A)sin(B)cos(C) + cos(A)cos(B)sin(C) - sin(A)sin(B)sin(C)","sin(A)cos(B)cos(C) + cos(A)sin(B)cos(C) + cos(A)cos(B)sin(C) - sin(A)sin(B)sin(C)","sin(A)cos(B)cos(C) + cos(A)sin(B)cos(C) + cos(A)cos(B)sin(C) + sin(A)sin(B)sin(C)",3))
                questionsArray.add(Questions("cos(A+B+C) = ","cos(A)cos(B)cos(C) - cos(A)sin(B)sin(C) - sin(A)cos(B)sin(C) - cos(A)sin(B)sin(C)","cos(A)cos(B)cos(C) + cos(A)sin(B)sin(C) + sin(A)cos(B)sin(C) - cos(A)sin(B)sin(C)","cos(A)cos(B)cos(C) + cos(A)sin(B)sin(C) - sin(A)cos(B)sin(C) - cos(A)sin(B)sin(C)","cos(A)cos(B)cos(C) - cos(A)sin(B)sin(C) - sin(A)cos(B)sin(C) + cos(A)sin(B)sin(C)",1))
            }

        }
    }
}

data class Questions (val ques : String, val op1 : String, val op2 : String, val op3 : String,val op4 : String, val ans : Int)

