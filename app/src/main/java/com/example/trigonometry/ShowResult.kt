package com.example.trigonometry

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
    private val totalScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)

        result_recycler_view.layoutManager = LinearLayoutManager(this)
        result_recycler_view.adapter = adapter

        userAnswers = intent.getIntegerArrayListExtra("resultOfQuiz")

        for (i in userAnswers){
            Log.wtf("theResultPassed","$i")
        }

        addResultItemsToRecyclerView()

    }

    private fun addResultItemsToRecyclerView(){
        for ((index,i) in userAnswers.withIndex()){
            adapter.add(ResultItem(i,index))
        }
      //  adapter.add(FinalScoreItem())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.getItemId()

        // click on icon to go back
        //triangle icon on the main android toolbar.

        return if (id == android.R.id.home) {
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }

    

    class ResultItem(private val userAnswer : Int, private val index: Int) : Item<ViewHolder>(){
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
                viewHolder.itemView.your_answer_result.setBackgroundColor(Color.GREEN)
            }
            else{
                viewHolder.itemView.your_answer_result.setBackgroundColor(Color.RED)
            }
            val correctAnswerText = "Correct Answer: "

            when {
                quizQ!!.questionsArray[index].ans == 1 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op1
                quizQ!!.questionsArray[index].ans == 2 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op2
                quizQ!!.questionsArray[index].ans == 3 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op3
                quizQ!!.questionsArray[index].ans == 4 -> viewHolder.itemView.correct_answer_text_view.text = correctAnswerText+quizQ!!.questionsArray[index].op3
            }
            viewHolder.itemView.correct_answer_text_view.setBackgroundColor(Color.GREEN)

        }
    }
}

