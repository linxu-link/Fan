package com.link.librarymodule.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentTransaction
import com.link.general_statelayout.StateLayoutManager
import com.link.librarymodule.R
import com.link.librarymodule.receiver.NetworkConnectChangedReceiver

const val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"

const val SHOW_CONTENT = 0x2001

/**
 * @author WJ
 * @date 2019-08-19
 *
 * 描述：控制页面状态的基础fragment
 */
abstract class BaseStateFragment : BaseFragment() {

    protected var mStatusLayoutManager: StateLayoutManager? = null
    protected var mActivity: Activity? = null

    //默认的网络错误布局id
    protected var mNetworkErrorLayoutId: Int = R.layout.layout_network_error
    //默认的载入中布局id
    protected var mLoadingLayoutId: Int = R.layout.layout_loading
    //默认的数据错误布局id
    protected var mErrorLayoutId: Int = R.layout.layout_error
    //默认的空数据布局id
    protected var mEmptyLayoutId: Int = R.layout.layout_empty


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    /**
     * 异常崩溃后会再次走onCreate方法，这也就是为啥有时候fragment重叠，因为被创建多次
     * 发生Fragment重叠的根本原因在于FragmentState没有保存Fragment的显示状态，
     * 即mHidden，导致页面重启后，该值为默认的false，即show状态，所以导致了Fragment的重叠。
     * 两种方案：第一种在activity中处理，第二种在fragment中处理
     * @param savedInstanceState                bundle
     */
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            //异常启动
            val isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)
            var ft: FragmentTransaction? = null
            if (fragmentManager != null) {
                ft = fragmentManager!!.beginTransaction()
                if (isSupportHidden) {
                    ft.hide(this)
                } else {
                    ft.show(this)
                }
                ft.commit()
            }
        } else {
            //正常启动
        }
    }

    /**
     * 异常崩溃，但是没有完全杀死app，内存重启，保存状态
     * @param outState                          bundle
     */
    override fun onSaveInstanceState(@NonNull outState: Bundle) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.layout_state_base, container, false)
            mRootView!!.isClickable = true
            initStatusLayout()
            initBaseView(mRootView!!)
        }
        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * 获取到子布局
     * @param mRootView              mRootView
     */
    open fun initBaseView(mRootView: View) {
        val flStateView = mRootView.findViewById<FrameLayout>(R.id.fl_state_view)
        flStateView.addView(mStatusLayoutManager!!.getRootLayout())
    }


    /**
     * 初始化状态管理器相关操作
     */
    protected abstract fun initStatusLayout()

    /**
     * 初始化View的代码写在这个方法中
     * @param mRootView  mRootView
     */
    /**
     * 初始化UI
     */
    open fun initView() {

    }




    /*---------------------------------下面是状态切换方法-----------------------------------------*/

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