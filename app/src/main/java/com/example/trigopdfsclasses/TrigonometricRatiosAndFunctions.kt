package com.example.trigopdfsclasses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trigonometry.R
import kotlinx.android.synthetic.main.activity_trigonometry_ratios_and_funtions.*

class TrigonometricRatiosAndFunctions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigonometry_ratios_and_funtions)
        pdf_view_ratios_functions.fromAsset("trigonometricratiosandfunctions.pdf").load()


    }
}
