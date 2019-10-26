package com.link.librarymodule.widgets.navgation

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.Gravity
import android.widget.RadioButton

class CustomRadioButton @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) : RadioButton(context, attributeSet) {

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val drawables = compoundDrawables
        val drawable = drawables[0]
        val gravity = gravity
        var left = 0
        if (gravity == Gravity.CENTER) {
            left = ((width - drawable.intrinsicWidth - paint.measureText(text.toString())) / 2).toInt()
        }
        drawable.setBounds(left, 0, left + drawable.intrinsicWidth, drawable.intrinsicHeight)

    }

}