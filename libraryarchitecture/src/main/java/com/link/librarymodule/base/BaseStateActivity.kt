package com.link.librarymodule.base

import android.os.Bundle
import android.widget.FrameLayout
import com.link.general_statelayout.StateLayoutManager
import com.link.librarymodule.R
import kotlinx.android.synthetic.main.layout_state_base.*

abstract class BaseStateActivity : BaseActivity() {

    protected var mStatusLayoutManager: StateLayoutManager? = null

    //默认的网络错误布局id
    protected var mNetworkErrorLayoutId: Int = R.layout.layout_network_error
    protected var mLoadingLayoutId: Int = R.layout.layout_loading
    protected var mErrorLayoutId: Int = R.layout.layout_error
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
        fl_state_view.addView(mStatusLayoutManager!!.getRootLayout())
    }

    protected fun showContent() {
        mStatusLayoutManager!!.showContent()
    }

    protected fun showEmptyData() {
        mStatusLayoutManager!!.showEmptyData()
    }

    protected fun showError() {
        mStatusLayoutManager!!.showError()
    }

    protected fun showNetWorkError() {
        mStatusLayoutManager!!.showNetWorkError()
    }

    protected fun showLoading() {
        mStatusLayoutManager!!.showLoading()
    }
}