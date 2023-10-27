package com.example.lab7.fourth

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.NumberPicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.lab7.R
import com.example.lab7.databinding.ActivityFourthTaskBinding

class FourthTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFourthTaskBinding.inflate(layoutInflater)
    }

    private var timer: CountDownTimer? = null
    private var isRunning = false
    private var selectedHours = 0
    private var selectedMinutes = 0
    private var selectedSeconds = 0
    private var lastSelectedTime = 0L
    private var remainingTime: Long = lastSelectedTime

    private var isPaused = false

    private fun initNumberPickers() {
        binding.hoursPicker.maxValue = 24
        binding.hoursPicker.minValue = 0
        binding.hoursPicker.setFormatter(object : NumberPicker.Formatter {
            override fun format(p0: Int): String {
                return String.format("%02d", p0)
            }
        })

        binding.hoursPicker.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            selectedHours = newVal
        }

        binding.minutesPicker.maxValue = 60
        binding.minutesPicker.minValue = 0
        binding.minutesPicker.setFormatter(object : NumberPicker.Formatter {
            override fun format(p0: Int): String {
                return String.format("%02d", p0)
            }
        })
        binding.minutesPicker.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            selectedMinutes = newVal
        }

        binding.secondsPicker.maxValue = 60
        binding.secondsPicker.minValue = 0
        binding.secondsPicker.setFormatter(object : NumberPicker.Formatter {
            override fun format(p0: Int): String {
                return String.format("%02d", p0)
            }
        })
        binding.secondsPicker.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            selectedSeconds = newVal
        }
    }

    private fun updateTimer() {
        lastSelectedTime = selectedHours.toLong() * 3600000 + selectedMinutes.toLong() * 60000 +
                selectedSeconds.toLong() * 1000
        binding.myCustomTextView.timeUntilEnd = lastSelectedTime
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnFabListeners()
        initNumberPickers()
    }

    private fun startTimer() {
        lockPickers()
        isPaused = false
        timer = object : CountDownTimer(lastSelectedTime, 1000) {
            override fun onTick(timeUntilEnd: Long) {
                binding.myCustomTextView.timeUntilEnd = timeUntilEnd
                remainingTime = timeUntilEnd
            }
            override fun onFinish() {
                stopTimer()
            }
        }
        timer?.start()
    }

    private fun resumeTimer() {
        lockPickers()
        isPaused = false
        timer = object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(timeUntilEnd: Long) {
                binding.myCustomTextView.timeUntilEnd = timeUntilEnd
                remainingTime = timeUntilEnd
            }
            override fun onFinish() {
                stopTimer()
            }
        }
        timer?.start()
    }

    private fun stopTimer() {
        binding.myCustomTextView.timeUntilEnd = lastSelectedTime
        isRunning = false
        binding.fabStart.setImageResource(R.drawable.play_arrow)
        Toast.makeText(this@FourthTaskActivity, "Ended", Toast.LENGTH_SHORT).show()
        timer?.cancel()
        timer = null
        unlockPickers()
    }

    private fun lockPickers() {
        updateTimer()
        binding.hoursPicker.visibility = View.GONE
        binding.minutesPicker.visibility = View.GONE
        binding.secondsPicker.visibility = View.GONE
        binding.myCustomTextView.visibility = View.VISIBLE
    }

    private fun unlockPickers() {
        binding.hoursPicker.visibility = View.VISIBLE
        binding.minutesPicker.visibility = View.VISIBLE
        binding.secondsPicker.visibility = View.VISIBLE
        binding.myCustomTextView.visibility = View.GONE
    }

    private fun pauseTimer() {
        binding.fabStart.setImageResource(R.drawable.play_arrow)
        isRunning = false
        isPaused = true
        timer?.cancel()
    }

    private fun setOnFabListeners() {
        binding.fabStart.setOnClickListener {
            if(isRunning) {
                pauseTimer()
            } else {
                if(timer == null) {
                    startTimer()
                } else {
                    resumeTimer()
                }
                isRunning = true
                binding.fabStart.setImageResource(R.drawable.baseline_pause_24)
            }
        }
        binding.fabStop.setOnClickListener {
            if(isRunning || isPaused) {
                stopTimer()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, FourthTaskActivity::class.java)
    }

}