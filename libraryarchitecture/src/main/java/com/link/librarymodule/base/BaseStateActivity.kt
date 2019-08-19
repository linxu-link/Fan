package com.link.librarymodule.base

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.FrameLayout
import com.link.general_statelayout.StateLayoutManager
import com.link.librarymodule.R
import com.link.librarymodule.receiver.NetworkConnectChangedReceiver

/**
 * @author WJ
 * @date 2019-08-19
 *
 * 描述：控制页面状态的基础activity
 * */
abstract class BaseStateActivity : BaseActivity() {

    protected var mStatusLayoutManager: StateLayoutManager? = null

    //默认的网络错误布局id
    protected var mNetworkErrorLayoutId: Int = R.layout.layout_network_error
    //默认的载入中布局id
    protected var mLoadingLayoutId: Int = R.layout.layout_loading
    //默认的数据错误布局id
    protected var mErrorLayoutId: Int = R.layout.layout_error
    //默认的空数据布局id
    protected var mEmptyLayoutId: Int = R.layout.layout_empty


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_state_base)
        initStatusLayout()
        initBaseView()
    }

    protected abstract fun initStatusLayout()

    private fun initBaseView() {
        val flStateView = findViewById<FrameLayout>(R.id.fl_state_view)
        flStateView.addView(mStatusLayoutManager!!.getRootLayout())
    }

    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg == null) {
                return
            }
            when (msg.what) {
                SHOW_CONTENT -> {
                    if (mStatusLayoutManager != null) {
                        mStatusLayoutManager!!.showContent()
                    }
                }
            }
        }

    }


    /**
     * 加载成功
     */
    protected fun showContent() {
        //延迟1秒种，让动画转几圈
        handler.sendEmptyMessageDelayed(SHOW_CONTENT, 1500)
    }

    /**
     * 加载无数据
     */
    protected fun showEmptyData() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager!!.showEmptyData()
        }
    }

    /**
     * 加载异常
     */
    protected fun showError() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager!!.showError()
        }
    }

    /**
     * 加载网络异常
     */
    protected fun showNetWorkError() {
        if (mStatusLayoutManager != null) {
            mStatusLayoutManager!!.showNetWorkError()
        }
    }

    /**
     * 加载loading
     */
    protected fun showLoading() {

        if (mStatusLayoutManager != null) {
            mStatusLayoutManager!!.showLoading()
        }
    }
}