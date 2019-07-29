package com.link.view_common.like

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.link.view_common.R

class LikeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var offsetX = 0
    private var offsetY = 0

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.GRAY
        mPaint.strokeWidth = 4f
//        val typeArray=context.obtainStyledAttributes(attrs, R.styleable.)
//        val width=
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        offsetX = width / 2
        offsetY = height / 2 - 55
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var angle = 10f
        while (angle < 20) {
            val p = getHeartPoint(angle)
            canvas.drawPoint(p.x.toFloat(), p.y.toFloat(), mPaint)
            angle = angle + 0.02f
        }

    }


    fun getHeartPoint(angle: Float): Point {
        val t = (angle / Math.PI).toFloat()
        val x = (3 * (16 * Math.pow(Math.sin(t.toDouble()), 3.0))).toFloat()
        val y = (-3 * (13 * Math.cos(t.toDouble()) - 5 * Math.cos((2 * t).toDouble()) - 2 * Math.cos((3 * t).toDouble()) - Math.cos((4 * t).toDouble()))).toFloat()
        return Point(offsetX + x.toInt(), offsetY + y.toInt())
    }

}