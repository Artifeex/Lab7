package com.example.lab7.first

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab7.R
import com.example.lab7.databinding.ActivityFirstTaskBinding
import java.util.Timer
import java.util.TimerTask

class FirstTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFirstTaskBinding.inflate(layoutInflater)
    }

    private var animationRunning = false
    private var counter: Int = 0
    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        timer = Timer() //timer - это отедельный поток
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
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
                            if(!animationRunning) {
                                startRunningMan()
                                animationRunning = true
                            }
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

    private fun startRunningMan() {
        val endPosition = -binding.root.width * 3.0f / 4
        val startPosition = binding.runningMan.translationX

        val animatorFromRightToLeft = createObjectAnimation(binding.runningMan, "translationX", 3000 , endPosition)
        val rotationForYFirst = createObjectAnimation(binding.runningMan, "rotationY", 3000 ,0f, 180f)
        val rotationForYSecond = createObjectAnimation(binding.runningMan, "rotationY", 3000 ,180f, 0f)
        val animatorFromLeftToRight = createObjectAnimation(binding.runningMan, "translationX", 3000, startPosition)
        val s = AnimatorSet()
        s.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animation.start()
            }
        })
        s.play(animatorFromRightToLeft).before(rotationForYFirst)
        s.play(rotationForYFirst).before(animatorFromLeftToRight)
        s.play(animatorFromLeftToRight).before(rotationForYSecond)
        s.start()

    }

    private fun createObjectAnimation(view: View, property: String, duration: Long, vararg values: Float): ObjectAnimator {
        val objAnimator = ObjectAnimator.ofFloat(view, property, *values)
        objAnimator.repeatMode = ObjectAnimator.RESTART
        objAnimator.repeatCount = 0
        objAnimator.duration = duration
        return objAnimator
    }

    companion object {
        private val RED_LIGHT_ON = 0
        private val RED_LIGHT_OFF = 3
        private val YELLOW_LIGHT_ON = 2
        private val GREEN_LIGHT_OFF = 6

        fun newIntent(context: Context) = Intent(context, FirstTaskActivity::class.java)
    }
}