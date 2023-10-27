package com.example.lab7.third

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab7.R

class ThirdTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_task)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ThirdTaskActivity::class.java)
    }
}