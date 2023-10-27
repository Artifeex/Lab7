package com.example.lab7.fifth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.lab7.R
import com.example.lab7.databinding.ActivityFifthTaskBinding

class FifthTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFifthTaskBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.bnAddFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, Frag())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, FifthTaskActivity::class.java)
    }
}