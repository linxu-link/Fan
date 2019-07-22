package com.link.librarymodule.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.link.librarymodule.R
import com.link.librarymodule.utils.ToastUtils


class HorizontalBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mLineWidth = 10

    var mMaxNum: Int = 0

    var mCurrentLineWidth: Float = 0f
        set(value) {
            field = value
            invalidate()
        }


    var mCurrentNum: Float = 0f
        set(value) {
            if (mMaxNum == 0) {
                throw RuntimeException("please set the MaxNum first")
            }
            val end = value / mMaxNum * mLineWidth.toFloat() - 20f
            getLineAnimator(field, end)!!.start()
            field = end
        }

    init {
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 16f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mLineWidth < w) {
            mLineWidth = w
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = context.resources.getColor(R.color.grey)
        canvas!!.drawLine(20f, 30f, mLineWidth.toFloat() - 20f, 30f, mPaint)

        if (mCurrentLineWidth > 0f) {
            mPaint.color = context.resources.getColor(R.color.colorPrimary)
            canvas.drawLine(20f, 30f, mCurrentLineWidth, 30f, mPaint)
        }
    }

    private var mLineAnimator: ObjectAnimator? = null

    private fun getLineAnimator(start: Float, end: Float): ObjectAnimator? {
        if (mLineAnimator == null) {
            mLineAnimator = ObjectAnimator.ofFloat(this, "mCurrentLineWidth", start, end)
        }
        mLineAnimator!!.setFloatValues(start, end)
        return mLineAnimator
    }


}