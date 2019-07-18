package com.example.trigonometry

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trigonometry.QuizActivity.Companion.quiz
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_show_result.*
import kotlinx.android.synthetic.main.result_item_recyler_view.view.*

class ShowResult : AppCompatActivity() {

    private var userAnswers = ArrayList<Int>()
    private val adapter = GroupAdapter<ViewHolder>()


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
    }

    class ResultItem(private val userAnswer : Int, private val index: Int) : Item<ViewHolder>(){
        override fun getLayout(): Int {
            return R.layout.result_item_recyler_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.question_view_result_item.text = quiz!!.questionsArray[index].ques
            when (userAnswer) {
                1 -> viewHolder.itemView.your_answer_result.text = quiz!!.questionsArray[index].op1
                2 -> viewHolder.itemView.your_answer_result.text = quiz!!.questionsArray[index].op2
                3 -> viewHolder.itemView.your_answer_result.text = quiz!!.questionsArray[index].op3
                0 -> viewHolder.itemView.your_answer_result.text = "No answer selected"
            }
            if (userAnswer == quiz!!.questionsArray[index].ans){
                viewHolder.itemView.your_answer_result.setBackgroundColor(Color.GREEN)
            }
            else{
                viewHolder.itemView.your_answer_result.setBackgroundColor(Color.RED)
            }

            when {
                quiz!!.questionsArray[index].ans == 1 -> viewHolder.itemView.correct_answer_text_view.text = quiz!!.questionsArray[index].op1
                quiz!!.questionsArray[index].ans == 2 -> viewHolder.itemView.correct_answer_text_view.text = quiz!!.questionsArray[index].op2
                quiz!!.questionsArray[index].ans == 3 -> viewHolder.itemView.correct_answer_text_view.text = quiz!!.questionsArray[index].op3
            }

        }
    }
}

