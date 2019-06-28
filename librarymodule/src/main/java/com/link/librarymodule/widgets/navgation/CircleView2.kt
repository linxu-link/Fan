package com.link.librarymodule.widgets.navgation

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import com.link.librarymodule.R


class CircleView2 @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : ImageView(context, attributeSet, defStyleAttr) {

    private var signle_width = 216f
    private var margin_top = 100f
    private val offsetX = 20f

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPath = Path()

    init {
        mPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        signle_width = width / 5f
        Log.e(": ", "${signle_width}")
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath.moveTo(0f, margin_top)
        mPath.cubicTo(signle_width / 2 - offsetX, margin_top, offsetX, margin_top + signle_width, signle_width / 2, margin_top + signle_width)
        mPath.cubicTo(signle_width-offsetX,signle_width+margin_top,signle_width/2+offsetX,margin_top,signle_width,margin_top)
        mPath.lineTo(width.toFloat(), margin_top)
        canvas!!.drawPath(mPath, mPaint)
    }


}