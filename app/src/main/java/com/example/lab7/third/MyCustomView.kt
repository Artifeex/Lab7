package com.example.lab7.third

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.lab7.R

class MyCustomView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    var buttonBackgroundColor: Int? = null
    var buttonText: String? = null


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr,
        R.style.MostDefaultBackbround
    )
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs,
        R.attr.buttonBackgroundAttr
    )
    constructor(context: Context): this(context, null)

    init {
        //значение для ориентации по умолчанию
        orientation = VERTICAL
        initializeAttrs(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttrs(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if(attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs,
            R.styleable.MyCustomView, defStyleAttr, defStyleRes)
        buttonBackgroundColor = typedArray.getColor(R.styleable.MyCustomView_buttonBackgroundColor, Color.BLACK)
        typedArray.recycle()
    }

    private fun getButtonTextFromChild() {
        if(childCount == 0) return
        val myChild = getChildAt(0)
        if(myChild is TextView) {
            buttonText = myChild.text.toString()
            removeView(myChild)
        }
    }

    private fun addButtonToMyView() {
        val button = AppCompatButton(context)
        button.backgroundTintList = ColorStateList.valueOf(buttonBackgroundColor ?: Color.BLACK)
        button.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        button.setTextColor(resources.getColor(R.color.white))
        button.setOnClickListener {
            Toast.makeText(context, "Button is clicked", Toast.LENGTH_SHORT).show()
        }

        button.text = buttonText
        this.addView(button)
    }

    override fun onFinishInflate() {
        getButtonTextFromChild()
        addButtonToMyView()
        super.onFinishInflate()
    }
}