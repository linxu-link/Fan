package com.link.librarymodule.widgets.navgation

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import com.link.librarymodule.R


class CircleView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : ImageView(context, attributeSet, defStyleAttr) {

    private val DEFAULT_CIRCLE_BACKGROUND_COLOR = Color.TRANSPARENT
    //背景颜色
    private var mCircleBackgroundColor = DEFAULT_CIRCLE_BACKGROUND_COLOR


    private var mBitmap: Bitmap? = null
    private var mShader: BitmapShader? = null
    private var mBitmapWidth: Int = 0
    private var mBitmapHeight: Int = 0

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mCircleBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mDrawableRect = RectF()
    private var mDrawableRadius = 0f

    init {

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CircleView, defStyleAttr, 0)
        mCircleBackgroundColor = typedArray.getColor(R.styleable.CircleView_circle_background_color, DEFAULT_CIRCLE_BACKGROUND_COLOR)
        mBitmap = getBitmapFromDrawable(typedArray.getResourceId(R.styleable.CircleView_circle_src,R.mipmap.background))
        typedArray.recycle()

        setLayerType(LAYER_TYPE_SOFTWARE,null)

        mCircleBackgroundPaint.color = mCircleBackgroundColor
        mShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        mBitmapHeight = mBitmap!!.width
        mBitmapWidth = mBitmap!!.height

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mBitmap == null) {
            return
        }
        if (mCircleBackgroundColor != Color.TRANSPARENT) {
            canvas!!.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRadius, mCircleBackgroundPaint);
        }
        mPaint.shader = mShader
        mPaint.setShadowLayer(5f,10f,10f,Color.GRAY)
        canvas!!.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRadius, mPaint)


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val width = width - paddingLeft - paddingRight
        val height = height - paddingTop - paddingBottom

        val left = (getWidth() - width) / 2f
        val top = (getWidth() - height) / 2f
        val bottom = top + height
        val right = left + width

        mDrawableRect.set(left, top, right, bottom)
        mDrawableRadius = Math.min(mDrawableRect.width() / 2, mDrawableRect.height() / 2)
        invalidate()
    }


    fun setImageDrawable(res: Int) {
        mBitmap = getBitmapFromDrawable(res)
        mShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mBitmapHeight = mBitmap!!.width
        mBitmapWidth = mBitmap!!.height
    }

    fun setCircleBackgroundColor(@ColorInt circleBackgroundColor: Int) {
        if (circleBackgroundColor == mCircleBackgroundColor) {
            return
        }
        mCircleBackgroundColor = circleBackgroundColor
        mCircleBackgroundPaint.color = circleBackgroundColor
        invalidate()
    }


    private fun getBitmapFromDrawable(res: Int): Bitmap? {
        return BitmapFactory.decodeResource(resources, res)
    }


}