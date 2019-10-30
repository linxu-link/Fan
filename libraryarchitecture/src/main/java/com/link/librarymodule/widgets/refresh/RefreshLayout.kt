package com.link.librarymodule.widgets.refresh

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.link.librarymodule.R

const val STATUS_ON_REFRESH = 0

const val STATUS_COMMON = 1

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-30-19:06
 *  email:wujia0916@thundersoft.com
 *  description:  
 * <pre>
 */
class RefreshLayout @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attributeSet, defStyleAttr), View.OnTouchListener {


    private val mHeaderView: View = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh, null, true)

    private var mProgressbar: ProgressBar
    private var mRefreshText: TextView
    private var mRefreshArrow: ImageView

    init {

        mProgressbar = mHeaderView.findViewById(R.id.refresh_pb)
        mRefreshText = mHeaderView.findViewById(R.id.refresh_text)
        mRefreshArrow = mHeaderView.findViewById(R.id.refresh_arrow)

        orientation = VERTICAL
        addView(mHeaderView, 0)

    }

    private var mIsLayout: Boolean = false
    private var mHideHeaderHeight = 0
    //maximum distance of pull
    private var mPullLength = 0
    //
    private var mHeaderLayoutParams: MarginLayoutParams? = null

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (!mIsLayout) {
            mHideHeaderHeight = -mHeaderView.height
            mPullLength = mHideHeaderHeight / 4 * 3
            mHeaderLayoutParams = mHeaderView.layoutParams as MarginLayoutParams?
            //hide the headerView
            mHeaderLayoutParams!!.topMargin = mHideHeaderHeight
            val listView = getChildAt(1)
            listView.setOnTouchListener(this)
            mIsLayout = true
        }
    }




    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        when (event!!.action) {

            MotionEvent.ACTION_DOWN -> {

            }

            MotionEvent.ACTION_MOVE -> {

            }

            MotionEvent.ACTION_UP -> {

            }

        }

        return true

    }

    private fun ablePull(){

    }

    private fun onRefresh() {
        if (mRefreshListener != null) {
            mRefreshListener!!.onRefresh()
        }
    }


    var mRefreshListener: RefreshListener? = null


    interface RefreshListener {

        fun onRefresh()

    }
}