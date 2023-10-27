package com.example.lab7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab7.databinding.ActivityMainBinding
import com.example.lab7.fifth.FifthTaskActivity
import com.example.lab7.first.FirstTaskActivity
import com.example.lab7.fourth.FourthTaskActivity
import com.example.lab7.second.SecondTaskActivity
import com.example.lab7.sixth.SixthTaskActivity
import com.example.lab7.third.ThirdTaskActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButtonsListeners()
    }



    private fun setupButtonsListeners() {
        binding.bnTask1.setOnClickListener {
            startActivity(FirstTaskActivity.newIntent(this))
        }
        binding.bnTask2.setOnClickListener {
            startActivity(SecondTaskActivity.newIntent(this))
        }
        binding.bnTask3.setOnClickListener {
            startActivity(FifthTaskActivity.newIntent(this))
        }
        binding.bnTask4.setOnClickListener {
            startActivity(ThirdTaskActivity.newIntent(this))

        }
        binding.bnTask5.setOnClickListener {
            startActivity(FourthTaskActivity.newIntent(this))
        }
        binding.bnTask6.setOnClickListener {
            startActivity(SixthTaskActivity.newIntent(this))
        }

    }
}