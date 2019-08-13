package com.example.trigonometry

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trigonometry.QuizActivity.Companion.quizQ
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_show_result.*
import kotlinx.android.synthetic.main.result_item_recyler_view.view.*

class ShowResult : AppCompatActivity() {

    private var userAnswers = ArrayList<Int>()
    private val adapter = GroupAdapter<ViewHolder>()
    private var totalScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)
        result_recycler_view.layoutManager = LinearLayoutManager(this)
        result_recycler_view.adapter = adapter

        userAnswers = intent.getIntegerArrayListExtra("resultOfQuiz")
        getTotalScore()

        store()
        addResultItemsToRecyclerView()
    }

    private fun getTotalScore() {
        for (i in 0 until userAnswers.size){
            if (userAnswers[i] == quizQ!!.questionsArray[i].ans){
                totalScore++
            }
        }
        Log.wtf("ScoreScore","the totals Score is si $totalScore")
    }


    private fun store() {
        total_score_view.text = total_score_view.text.toString()+" $totalScore/${userAnswers.size}"
        if (quizQ == null){
            return
        }

        if  (testScore(quizQ?.quizName) < totalScore!!){
            saveData(quizQ?.quizName!!)
        }
        totalScore = 0
    }

    private fun addResultItemsToRecyclerView(){
        for ((index,i) in userAnswers.withIndex()) {
            val resultItem = ResultItem(i, index)
            adapter.add(resultItem)
        }
    }

    private fun testScore(quizName: String?): Int {
        var score = 0
        if (quizName != null) {
            score = getData(quizName)
        }
        return score
    }

    private fun saveData(quiz: String) {
        val sharedPreferences = getSharedPreferences("testScores", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(quiz, totalScore!!)
        editor.apply()
    }

    private fun getData(quiz: String): Int {
        val sharedPreferences = getSharedPreferences("testScores", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(quiz, 0)
    }





    class ResultItem(private val userAnswer : Int, private val index: Int) : Item<ViewHolder>(){
        companion object{
            var ts = 0
        }

        override fun getLayout(): Int {
            return R.layout.result_item_recyler_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            val yourAnswerText = "Your Answer: "
            viewHolder.itemView.question_view_result_item.text = quizQ!!.questionsArray[index].ques
            when (userAnswer) {
                1 -> viewHolder.itemView.your_answer_result.text = yourAnswerText+quizQ!!.questionsArray[index].op1
                2 -> viewHolder.itemView.your_answer_result.text = yourAnswerText+quizQ!!.questionsArray[index].op2
                3 -> viewHolder.itemView.your_answer_result.text = yourAnswerText+quizQ!!.questionsArray[index].op3
                4 -> viewHolder.itemView.your_answer_result.text = yourAnswerText+quizQ!!.questionsArray[index].op4
                0 -> viewHolder.itemView.your_answer_result.text = yourAnswerText+"No answer selected"
            }
            if (userAnswer == quizQ!!.questionsArray[index].ans){
                viewHolder.itemView.result_imageView.setImageResource(R.drawable.checkedhaiye)
                ts++
                Log.wtf("scoreme","ts is ${ts}")
                // store()
            }
            else if (userAnswer != quizQ!!.questionsArray[index].ans){
                if(userAnswer == 0) viewHolder.itemView.result_imageView.setImageResource(R.drawable.warning)
                else viewHolder.itemView.result_imageView.setImageResource(R.drawable.cancelhiaye)
            }
            val correctAnswerText = "Correct Answer: "

            when {
                quizQ!!.questionsArray[index].ans == 1 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op1
                quizQ!!.questionsArray[index].ans == 2 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op2
                quizQ!!.questionsArray[index].ans == 3 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op3
                quizQ!!.questionsArray[index].ans == 4 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op3
            }
            //viewHolder.itemView.correct_answer_text_view.setBackgroundColor(Color.GREEN)

        }
    }
}

