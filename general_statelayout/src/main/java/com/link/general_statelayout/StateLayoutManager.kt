package com.link.general_statelayout

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.annotation.LayoutRes
import com.link.general_statelayout.listener.OnNetworkListener
import com.link.general_statelayout.listener.OnRetryListener
import com.link.general_statelayout.listener.OnShowOrHideListener

class StateLayoutManager constructor(builder: Builder, warpContent: Boolean) {

    var context: Context
    var loadingLayoutResId = 0
    var contentLayoutResId: Int = 0
    var netWorkErrorVs: ViewStub? = null
    var netWorkErrorRetryViewId: Int = 0
    var emptyDataVs: ViewStub? = null
    var emptyDataRetryViewId: Int = 0
    var errorVs: ViewStub? = null
    var errorRetryViewId: Int = 0
    var retryViewId: Int = 0
    var emptyDataIconImageId: Int = 0
    var emptyDataTextTipId: Int = 0
    var errorIconImageId: Int = 0
    var errorTextTipId: Int = 0
    var errorLayout: AbsViewStubLayout? = null
    var emptyDataLayout: AbsViewStubLayout? = null
    var onShowHideViewListener: OnShowOrHideListener? = null
    var onRetryListener: OnRetryListener? = null
    var onNetworkListener: OnNetworkListener? = null

    private val rootFrameLayout: StateFrameLayout

    companion object {
        fun newBuilder(context: Context, warpContent: Boolean = false): Builder {
            return if (warpContent) {
                Builder(context)
            } else {
                Builder(context, warpContent)
            }
        }

        class Builder constructor(var context: Context, var warpContent: Boolean = false) {

            var loadingLayoutResId = 0
            var contentLayoutResId: Int = 0
            var netWorkErrorVs: ViewStub? = null
            var netWorkErrorRetryViewId: Int = 0
            var emptyDataVs: ViewStub? = null
            var emptyDataRetryViewId: Int = 0
            var errorVs: ViewStub? = null
            var errorRetryViewId: Int = 0
            var retryViewId: Int = 0
            var emptyDataIconImageId: Int = 0
            var emptyDataTextTipId: Int = 0
            var errorIconImageId: Int = 0
            var errorTextTipId: Int = 0
            var errorLayout: AbsViewStubLayout? = null
            var emptyDataLayout: AbsViewStubLayout? = null
            var onShowHideViewListener: OnShowOrHideListener? = null
            var onRetryListener: OnRetryListener? = null
            var onNetworkListener: OnNetworkListener? = null

            fun loadingView(@LayoutRes loadingLayoutResId: Int): Builder {
                this.loadingLayoutResId = loadingLayoutResId
                return this
            }

            fun networkErrorView(@LayoutRes networkErrorId: Int): Builder {
                this.netWorkErrorVs = ViewStub(context)
                netWorkErrorVs!!.layoutResource = networkErrorId
                return this
            }

            /**
             * 自定义加载空数据布局
             */
            fun emptyDataView(@LayoutRes noDataViewId: Int): Builder {
                emptyDataVs = ViewStub(context)
                emptyDataVs!!.setLayoutResource(noDataViewId)
                return this
            }

            /**
             * 自定义加载错误布局
             */
            fun errorView(@LayoutRes errorViewId: Int): Builder {
                errorVs = ViewStub(context)
                errorVs!!.layoutResource = errorViewId
                return this
            }

            /**
             * 自定义加载内容正常布局
             */
            fun contentView(@LayoutRes contentLayoutResId: Int): Builder {
                this.contentLayoutResId = contentLayoutResId
                return this
            }

            /**
             * 自定义异常布局
             * @param errorLayout                   error
             * @return
             */
            fun errorLayout(errorLayout: AbsViewStubLayout): Builder {
                this.errorLayout = errorLayout
                this.errorVs = errorLayout.getLayoutVs()
                return this
            }

            /**
             * 自定义空数据布局
             * @param emptyDataLayout              emptyDataLayout
             * @return
             */
            fun emptyDataLayout(emptyDataLayout: AbsViewStubLayout): Builder {
                this.emptyDataLayout = emptyDataLayout
                this.emptyDataVs = emptyDataLayout.getLayoutVs()
                return this
            }

            fun netWorkErrorRetryViewId(@LayoutRes netWorkErrorRetryViewId: Int): Builder {
                this.netWorkErrorRetryViewId = netWorkErrorRetryViewId
                return this
            }

            fun emptyDataRetryViewId(@LayoutRes emptyDataRetryViewId: Int): Builder {
                this.emptyDataRetryViewId = emptyDataRetryViewId
                return this
            }

            fun errorRetryViewId(@LayoutRes errorRetryViewId: Int): Builder {
                this.errorRetryViewId = errorRetryViewId
                return this
            }

            fun retryViewId(@LayoutRes retryViewId: Int): Builder {
                this.retryViewId = retryViewId
                return this
            }

            fun emptyDataIconImageId(@LayoutRes emptyDataIconImageId: Int): Builder {
                this.emptyDataIconImageId = emptyDataIconImageId
                return this
            }

            fun emptyDataTextTipId(@LayoutRes emptyDataTextTipId: Int): Builder {
                this.emptyDataTextTipId = emptyDataTextTipId
                return this
            }

            fun errorIconImageId(@LayoutRes errorIconImageId: Int): Builder {
                this.errorIconImageId = errorIconImageId
                return this
            }

            fun errorTextTipId(@LayoutRes errorTextTipId: Int): Builder {
                this.errorTextTipId = errorTextTipId
                return this
            }

            /**
             * 为状态View显示隐藏监听事件
             * @param listener                  listener
             * @return
             */
            fun onShowHideViewListener(listener: OnShowOrHideListener): Builder {
                this.onShowHideViewListener = listener
                return this
            }

            /**
             * 为重试加载按钮的监听事件
             * @param onRetryListener           listener
             * @return
             */
            fun onRetryListener(onRetryListener: OnRetryListener): Builder {
                this.onRetryListener = onRetryListener
                return this
            }

            /**
             * 为重试加载按钮的监听事件
             * @param onNetworkListener           listener
             * @return
             */
            fun onNetworkListener(onNetworkListener: OnNetworkListener): Builder {
                this.onNetworkListener = onNetworkListener
                return this
            }

            fun build(): StateLayoutManager {
                return StateLayoutManager(this, warpContent)
            }

        }

    }

    init {
        this.context = builder.context
        this.loadingLayoutResId = builder.loadingLayoutResId
        this.netWorkErrorVs = builder.netWorkErrorVs
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId
        this.emptyDataVs = builder.emptyDataVs
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId
        this.errorVs = builder.errorVs
        this.errorRetryViewId = builder.errorRetryViewId
        this.contentLayoutResId = builder.contentLayoutResId
        this.onShowHideViewListener = builder.onShowHideViewListener
        this.retryViewId = builder.retryViewId
        this.onRetryListener = builder.onRetryListener
        this.onNetworkListener = builder.onNetworkListener
        this.emptyDataIconImageId = builder.emptyDataIconImageId
        this.emptyDataTextTipId = builder.emptyDataTextTipId
        this.errorIconImageId = builder.errorIconImageId
        this.errorTextTipId = builder.errorTextTipId
        this.errorLayout = builder.errorLayout
        this.emptyDataLayout = builder.emptyDataLayout

        //创建帧布局
        rootFrameLayout = StateFrameLayout(this.context)
        val layoutParams: ViewGroup.LayoutParams
        //是否包裹内容
        if (warpContent) {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        rootFrameLayout.setLayoutParams(layoutParams)
        //设置为白色
        rootFrameLayout.setBackgroundColor(Color.WHITE)

        //设置状态管理器
        rootFrameLayout.mStatusLayoutManager = this
    }

    /**
     * 判断是否在showLoading中
     * @return
     */
    fun isShowLoading(): Boolean {
        return rootFrameLayout.isLoading()
    }

    /**
     * 显示loading
     */
    fun showLoading() {
        if (!isShowLoading()) {
            rootFrameLayout.showLoading()
        }
    }

    /**
     * 显示内容
     */
    fun showContent() {
        rootFrameLayout.showContent()
    }

    /**
     * 显示空数据
     */
    fun showEmptyData(iconImage: Int, textTip: String) {
        rootFrameLayout.showEmptyData(iconImage, textTip)
    }


    /**
     * 显示空数据
     */
    fun showEmptyData() {
        showEmptyData(0, "")
    }

    /**
     * 显示空数据
     */
    fun showLayoutEmptyData(vararg objects: Any) {
        rootFrameLayout.showLayoutEmptyData(objects)
    }

    /**
     * 显示网络异常
     */
    fun showNetWorkError() {
        rootFrameLayout.showNetworkError()
    }

    /**
     * 显示异常
     */
    fun showError(iconImage: Int, textTip: String) {
        rootFrameLayout.showError(iconImage, textTip)
    }

    /**
     * 显示异常
     */
    fun showError() {
        showError(0, "")
    }

    /**
     * 显示异常
     * @param objects               objects
     */
    fun showLayoutError(vararg objects: Any) {
        rootFrameLayout.showLayoutError(objects)
    }

    /**
     * 得到root 布局
     */
    fun getRootLayout(): View {
        return rootFrameLayout
    }


}