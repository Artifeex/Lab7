package com.example.lab7.second

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.lab7.R
import com.example.lab7.databinding.ActivitySecondTaskBinding

class SecondTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondTaskBinding.inflate(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvMyText.setOnTouchListener { view, motionEvent ->
            val event = motionEvent.action
            if(event == MotionEvent.ACTION_DOWN) {
                val colorAnimation = ObjectAnimator.ofArgb(view, "textColor", getColor(R.color.black) , getColor(
                    R.color.green
                ))
                colorAnimation.duration = 3000
                colorAnimation.repeatMode = ObjectAnimator.REVERSE
                colorAnimation.repeatCount = ObjectAnimator.INFINITE
                colorAnimation.start()
                view.animate().apply {
                    duration = 3000
                    translationY(binding.root.height.toFloat() / 2)
                    rotation(180f)
                }
            } else if(event == MotionEvent.ACTION_UP) {
                view.animate().apply {
                    duration = 3000
                    translationY(0f)
                    rotation(0f)
                }

            }

            true
        }

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.action

        return super.onTouchEvent(event)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, SecondTaskActivity::class.java)
    }
}