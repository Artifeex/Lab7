package com.example.lab7.fifth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab7.R
import com.example.lab7.databinding.TrafficLightLayoutBinding
import com.example.lab7.first.FirstTaskActivity
import java.util.Timer
import java.util.TimerTask

class Frag: Fragment() {

    private var _binding: TrafficLightLayoutBinding? = null
    private val binding: TrafficLightLayoutBinding
        get() = _binding ?: throw NullPointerException("_binding: TrafficLight is null")

    private var timer: Timer? = null
    private var counter: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrafficLightLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun startAnimation() {
        timer = Timer() //timer - это отедельный поток
        timer?.schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    when(counter) {
                        RED_LIGHT_ON -> {
                            binding.redColor.setBackgroundResource(R.drawable.red_light)
                        }
                        YELLOW_LIGHT_ON -> {
                            binding.yellowColor.setBackgroundResource(R.drawable.yellow_light)
                        }
                        RED_LIGHT_OFF -> {
                            binding.redColor.setBackgroundResource(R.drawable.dis_light)
                            binding.yellowColor.setBackgroundResource(R.drawable.dis_light)
                            binding.greenColor.setBackgroundResource(R.drawable.green_light)
                        }
                        GREEN_LIGHT_OFF -> {
                            binding.redColor.setBackgroundResource(R.drawable.red_light)
                            binding.yellowColor.setBackgroundResource(R.drawable.dis_light)
                            binding.greenColor.setBackgroundResource(R.drawable.dis_light)
                            counter = 0
                        }
                    }
                    counter++
                }
            }
        }, 0,1000)
    }

    override fun onStart() {
        super.onStart()
        startAnimation()
    }

    override fun onStop() {
        super.onStop()
        stopAnimation()
    }

    private fun stopAnimation() {
        timer?.cancel()
    }

    companion object {
        private val RED_LIGHT_ON = 0
        private val RED_LIGHT_OFF = 3
        private val YELLOW_LIGHT_ON = 2
        private val GREEN_LIGHT_OFF = 6

    }
}