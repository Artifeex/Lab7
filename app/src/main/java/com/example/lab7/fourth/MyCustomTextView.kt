package com.example.lab7.fourth

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.lab7.R
import java.text.DecimalFormat

class MyCustomTextView (
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
): AppCompatTextView(context, attrs, defStyleAttr) {

    var timeUntilEnd: Long = 0
        set(value) {
            field = value
            //парсим данные в миллисекундах и правильно вставляем внутрь textView
            val numberFormat = DecimalFormat("00")
            val hours = (value / 3600000) % 24
            val min = (value / 60000) % 60
            val sec = value / 1000 % 60
            text = "${numberFormat.format(hours)}:${numberFormat.format(min)}:${numberFormat.format(sec)}"
        }

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, R.attr.myCustomTextViewAttr)
    constructor(context: Context): this(context, null)


    init {
        //layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        text = "00:00:00"
    }


}