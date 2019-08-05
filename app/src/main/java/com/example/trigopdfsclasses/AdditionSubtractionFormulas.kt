package com.example.trigopdfsclasses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trigonometry.R
import kotlinx.android.synthetic.main.activity_addition__sub_formulas.*

class AdditionSubtractionFormulas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition__sub_formulas)

        pdf_view_add_sub_functions.fromAsset("add_sub.pdf").load()
    }
}
