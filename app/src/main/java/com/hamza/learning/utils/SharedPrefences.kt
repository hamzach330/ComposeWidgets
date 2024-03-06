package com.hamza.learning.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hamza.learning.MainActivity

object SharedPreferenceUtil {
    private const val NAME = "onBoarding"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // call this method from application onCreate(once)
    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }


    fun getBoardingIsFinished(): Boolean {
        //val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return preferences.getBoolean("isFinished", false)

    }

    fun setBoardingIsFinished() {
       // val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean("isFinished", true)
        editor.apply()

    }



}