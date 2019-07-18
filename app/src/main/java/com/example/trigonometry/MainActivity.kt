package com.example.trigonometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_recycler_view_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = GroupAdapter<ViewHolder>()
        recycler_view_home.layoutManager = LinearLayoutManager(this)
        recycler_view_home.adapter = adapter

        addItemsToRecyclerHome(adapter)

        adapter.setOnItemClickListener { item, view ->
            when(view.item_title.text.toString()){
                "Values of Trigonometric Ratios" -> {
                    val intent = Intent(this, TrigonometricRationsValues::class.java)
                    startActivity(intent)
                }
                "Trigonometric Ratios and Functions" -> {
                    val intent = Intent(this, TrigonometryRatiosAndFuntions::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun addItemsToRecyclerHome(adapter : GroupAdapter<ViewHolder>){
        adapter.add(ActivitiesItem("Values of Trigonometric Ratios","Table of Trigonometric Standard Angles"))
        adapter.add(ActivitiesItem("Trigonometric Ratios and Functions", "Basic definitions"))
        adapter.add(ActivitiesItem("Addition and Subtraction Functions","The formulas with addition or subtraction in their angles"))
    }


}

class ActivitiesItem(val title: String, val dis: String) : Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.home_recycler_view_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.item_title.text = title
        viewHolder.itemView.item_discrip.text = dis

    }


}

