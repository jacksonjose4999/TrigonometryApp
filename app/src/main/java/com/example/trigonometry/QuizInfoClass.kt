package com.example.trigonometry

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class QuizInfoClass (val quizName : String, val currQuizScore : Int, val maxQuizScore : Int) : Parcelable