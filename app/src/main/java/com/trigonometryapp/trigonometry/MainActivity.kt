package com.trigonometryapp.trigonometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.trigonometryapp.trigopdfsclasses.*
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
        recycler_view_home.setBackgroundResource(R.drawable.crop)

        addItemsToRecyclerHome(adapter)

        adapter.setOnItemClickListener { item, view ->
            when (view.item_title.text.toString()) {
                "Trigonometric Ratios and Functions" -> {
                    val intent = Intent(this, TrigonometricRatiosAndFunctions::class.java)
                    startActivity(intent)
                }
                "Values of Trigonometric Ratios" -> {
                    val intent = Intent(this, TrigonometricRatiosAndValues::class.java)
                    startActivity(intent)
                }
                "Basic Trigonometric Functions" -> {
                    val intent = Intent(this, BasicTrigonometricFormulas::class.java)
                    startActivity(intent)
                }
                "Addition and Subtraction Functions" -> {
                    val intent = Intent(this, AdditionSubtractionFormulas::class.java)
                    startActivity(intent)
                }
                "Trigonometric Ratios and general Values" -> {
                    val intent = Intent(this, TrigoEquationsAndGeneralValues::class.java)
                    startActivity(intent)
                }
                "Conditional Identities" -> {
                    val intent = Intent(this, conditionalIdentities::class.java)
                    startActivity(intent)
                }
                "Transformation Formulae" -> {
                    val intent = Intent(this, Transformation::class.java)
                    startActivity(intent)
                }
                "Double, Triple and Half Angle Formulae" -> {
                    val intent = Intent(this, DoubleTripleHalf::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun addItemsToRecyclerHome(adapter: GroupAdapter<ViewHolder>) {
        adapter.add(ActivitiesItem("Trigonometric Ratios and Functions", "Basic formulas"))
        adapter.add(
            ActivitiesItem(
                "Values of Trigonometric Ratios",
                "Table of Trigonometric Standard Angles"
            )
        )
        adapter.add(ActivitiesItem("Basic Trigonometric Functions", "Basic definitions"))
        adapter.add(
            ActivitiesItem(
                "Addition and Subtraction Functions",
                "The formulas with addition or subtraction in their angles"
            )
        )
        adapter.add(
            ActivitiesItem(
                "Trigonometric Ratios and general Values",
                "Basic Trigonometric equations"
            )
        )
        adapter.add(
            ActivitiesItem(
                "Conditional Identities",
                "The formulas with addition or subtraction in their angles"
            )
        )
        adapter.add(
            ActivitiesItem(
                "Transformation Formulae",
                "Formulas to transform product into sum and vice versa"
            )
        )
        adapter.add(
            ActivitiesItem(
                "Double, Triple and Half Angle Formulae",
                " Formulae to manipulate angles"
            )
        )
    }


}

class ActivitiesItem(val title: String, val dis: String) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.home_recycler_view_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.item_title.text = title
        viewHolder.itemView.item_discrip.text = dis

    }
}

