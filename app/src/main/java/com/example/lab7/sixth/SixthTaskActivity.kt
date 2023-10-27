package com.example.lab7.sixth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab7.R
import com.example.lab7.databinding.ActivitySixthTaskBinding
import com.example.lab7.fifth.Frag

class SixthTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySixthTaskBinding.inflate(layoutInflater)
    }

    private var addedFragments: Int = 0
    private var removedFragments: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvCounterAdded.text = getString(R.string.counterAdded, addedFragments)
        binding.tvCounterRemoved.text = getString(R.string.counterRemoved, removedFragments)
        setListeners()
    }

    private fun addFragment() {
        addedFragments++
        binding.tvCounterAdded.text = getString(R.string.counterAdded, addedFragments)
    }

    private fun removeFragment() {
        removedFragments++
        binding.tvCounterRemoved.text = getString(R.string.counterRemoved, removedFragments)
    }

    private fun setListeners() {
        binding.addFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, Frag())
                .addToBackStack(null)
                .commit()
            addFragment()
        }
        binding.removeFragment.setOnClickListener {
            supportFragmentManager.popBackStack()
            if(supportFragmentManager.backStackEntryCount > 0) {
                removeFragment()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, SixthTaskActivity::class.java)
    }

}