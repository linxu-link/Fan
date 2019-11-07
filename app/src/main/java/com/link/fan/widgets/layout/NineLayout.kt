package com.link.fan.widgets.layout

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.link.fan.R
import com.link.general_picture.ImageLoader
import com.link.general_picture.glide.GlideStrategy
import java.util.*
import kotlin.collections.ArrayList

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-07-19:10
 * email:wujia0916@thundersoft.com
 * description:支持九宫格布局的layout
 * 步骤 1. 效果分析，自定义属性
2. 测量控件的宽高
3. 摆放控件的位置
4. 绘制控件
5. 用户交互（事件处理）
 */
const val MAX_COUNT = 9

abstract class NineLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    private var mSpacing = 3f

    private val mImgUrls = ArrayList<String>(9)

    private var mTotalWidth = 0
    private var mSingleWidth = 0
    private var mRows = 1
    private var mColumns = 1

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.NineLayout)
        mSpacing = typeArray.getDimension(R.styleable.NineLayout_spacing, mSpacing)
        typeArray.recycle()

        if (mImgUrls.isNullOrEmpty()) {
            visibility = View.GONE
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private var mIsFirst = false

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mTotalWidth = right - left
        mSingleWidth = ((mTotalWidth - mSpacing * 4) / 3).toInt()
        if (mIsFirst) {
            notifyDataSetChanged()
            mIsFirst = true
        }

    }

    fun setUrls(urls: List<String>) {
        if (urls.isNullOrEmpty()) {
            visibility = View.GONE
            return
        }
        visibility = View.VISIBLE
        mImgUrls.clear()
        mImgUrls.addAll(urls)

        if (!mIsFirst) {
            notifyDataSetChanged()
        }


    }

    private fun notifyDataSetChanged() {
        post(object : TimerTask() {
            override fun run() {
                refresh()
            }
        })
    }

    private fun refresh() {
        removeAllViews()
        val imgLength = mImgUrls.size
        visibility = if (imgLength != 0) {
            View.VISIBLE
        } else {
            View.GONE
        }
        //当只有一张图片时
        if (imgLength == 1) {
            val url = mImgUrls[0]
            val imageView = createImageView(0, url)
            val params = layoutParams
            params.height = mSingleWidth
            layoutParams = params
            imageView.layout(0, 0, mSingleWidth, mSingleWidth)
            addView(imageView)
            return
        }

        //设定行列
        when {
            imgLength <= 3 -> {
                mRows = 1
                mColumns = imgLength
            }
            imgLength <= 6 -> {
                mRows = 2
                mColumns = 3
            }
            imgLength <= 9 -> {
                mRows = 3
                mColumns = 3
            }
        }

        //根据子view的数量的确定layout的高度
        val params = layoutParams
        params.height = (mSingleWidth * mRows + mSpacing * (mRows - 1)).toInt()
        layoutParams = params

        //开始布局
        for (i in 0 until imgLength) {
            val url = mImgUrls[i]
            val imageView = createImageView(i, url)
            layoutImageView(imageView, i, url)
        }

    }

    /**
     * 摆放ImageView
     */
    private fun layoutImageView(imageView: ImageView, i: Int, url: String) {

        val singleWidth = (mTotalWidth - mSpacing * 4) / 3
        val singleHeight = singleWidth
        //确定ImageView的四个顶点
        val position = findPosition(i)
        val left = ((singleWidth + mSpacing) * position[1]).toInt()
        val top = ((singleHeight + mSpacing) * position[0]).toInt()
        val right = (left + singleWidth).toInt()
        val bottom = (top + singleHeight).toInt()
        imageView.layout(left, top, right, bottom)
        addView(imageView)
        displayImage(imageView, url)

    }

    private fun findPosition(childNum: Int): IntArray {
        val position = IntArray(2)
        for (i in 0 until mRows) {
            for (j in 0 until mColumns) {
                if (i * mColumns + j == childNum) {
                    position[0] = i//行
                    position[1] = j//列
                    break
                }
            }
        }
        return position
    }

    private fun createImageView(position: Int, url: String): ImageView {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setOnClickListener {
            onClickImage(position, url)
        }
        return imageView
    }

    abstract fun onClickImage(position: Int, url: String)

    //渲染图片到ImageView，只有一张图片时，单独渲染
    protected fun displayOneImage(imageView: ImageView, url: String) {
        ImageLoader.getInstance()
                .with(context)
                .load(url)
                .build(GlideStrategy())
                .into(imageView)
    }

    //渲染图片到ImageView
    protected fun displayImage(imageView: ImageView, url: String) {
        ImageLoader.getInstance()
                .with(context)
                .load(url)
                .build(GlideStrategy())
                .into(imageView)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

}