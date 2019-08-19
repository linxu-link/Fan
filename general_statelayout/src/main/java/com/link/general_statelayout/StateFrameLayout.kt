package com.link.general_statelayout

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes

/**
 * 载入中的布局Id
 */
const val LAYOUT_LOADING_ID = 0x0001

/**
 * 内容Id
 */
const val LAYOUT_CONTENT_ID = 0x0002

/**
 * 异常Id
 */
const val LAYOUT_ERROR_ID = 0x0003

/**
 * 网络异常Id
 */
const val LAYOUT_NETWORK_ERROR_ID = 0x0004

/**
 * 空数据Id
 */
const val LAYOUT_EMPTY_DATA_ID = 0x0005

class StateFrameLayout @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) : FrameLayout(context, attributeSet), ILayoutState {


    //存放布局的集合
    private val mLayoutArray = SparseArray<View>()

    var mStatusLayoutManager: StateLayoutManager? = null
        set(value) {
            field = value
            addAllLayoutToRootLayout()

        }

    /**
     * 添加所有不同状态布局到帧布局中
     */
    private fun addAllLayoutToRootLayout() {
        if (mStatusLayoutManager!!.contentLayoutResId !== 0) {
            addLayoutResId(mStatusLayoutManager!!.contentLayoutResId, LAYOUT_CONTENT_ID)
        }
        if (mStatusLayoutManager!!.loadingLayoutResId !== 0) {
            addLayoutResId(mStatusLayoutManager!!.loadingLayoutResId, LAYOUT_LOADING_ID)
        }

        if (mStatusLayoutManager!!.emptyDataVs != null) {
            addView(mStatusLayoutManager!!.emptyDataVs)
        }
        if (mStatusLayoutManager!!.errorVs != null) {
            addView(mStatusLayoutManager!!.errorVs)
        }
        if (mStatusLayoutManager!!.netWorkErrorVs != null) {
            addView(mStatusLayoutManager!!.netWorkErrorVs)
        }
    }

    private fun addLayoutResId(@LayoutRes layoutResId: Int, id: Int) {
        val resView = LayoutInflater.from(mStatusLayoutManager!!.context).inflate(layoutResId, null)
        mLayoutArray.put(id, resView)
        addView(resView)
    }


    /**
     * 判断是否正在loading中
     * @return                      true 表示loading正在加载中
     */
    override fun isLoading(): Boolean {
        val view = mLayoutArray.get(LAYOUT_LOADING_ID)
        return if (view != null) {
            view.visibility == View.VISIBLE
        } else {
            false
        }
    }

    /**
     * 显示loading
     */
    override fun showLoading() {
        if (mLayoutArray.get(LAYOUT_LOADING_ID) != null) {
            showHideViewById(LAYOUT_LOADING_ID)
        }
    }

    /**
     * 显示内容
     */
    override fun showContent() {
        if (mLayoutArray.get(LAYOUT_CONTENT_ID) != null) {
            showHideViewById(LAYOUT_CONTENT_ID)
        }
    }

    /**
     * 显示空数据
     */
    override fun showEmptyData(iconImage: Int, textTip: String) {
        if (inflateLayout(LAYOUT_EMPTY_DATA_ID)) {
            showHideViewById(LAYOUT_EMPTY_DATA_ID)
            emptyDataViewAddData(iconImage, textTip)
        }
    }

    override fun showNetworkError() {
        if (inflateLayout(LAYOUT_NETWORK_ERROR_ID)) {
            showHideViewById(LAYOUT_NETWORK_ERROR_ID)
        }
    }

    override fun showError(iconImage: Int, textTip: String) {
        if (inflateLayout(LAYOUT_ERROR_ID)) {
            showHideViewById(LAYOUT_ERROR_ID)
            errorViewAddData(iconImage, textTip)
        }
    }

    /**
     * 空数据并且设置页面简单数据
     * @param iconImage                 空页面图片
     * @param textTip                   文字
     */
    private fun emptyDataViewAddData(iconImage: Int, textTip: String) {
        if (iconImage == 0 && TextUtils.isEmpty(textTip)) {
            return
        }
        val emptyDataView = mLayoutArray.get(LAYOUT_EMPTY_DATA_ID)
        val iconImageView = emptyDataView.findViewById<View>(mStatusLayoutManager!!.emptyDataIconImageId)
        val textView = emptyDataView.findViewById<View>(mStatusLayoutManager!!.emptyDataTextTipId)
        if (iconImageView != null && iconImageView is ImageView) {
            iconImageView.setImageResource(iconImage)
        }

        if (textView != null && textView is TextView) {
            textView.text = textTip
        }
    }

    /**
     * 展示空页面
     * @param objects                   object
     */
    fun showLayoutEmptyData(vararg objects: Any) {
        if (inflateLayout(LAYOUT_EMPTY_DATA_ID)) {
            showHideViewById(LAYOUT_EMPTY_DATA_ID)

            val emptyDataLayout = mStatusLayoutManager!!.emptyDataLayout
            emptyDataLayout?.setData(objects)
        }
    }

    private fun errorViewAddData(iconImage: Int, textTip: String) {
        if (iconImage == 0 && TextUtils.isEmpty(textTip)) {
            return
        }
        val errorView = mLayoutArray.get(LAYOUT_ERROR_ID)
        val iconImageView = errorView.findViewById<View>(mStatusLayoutManager!!.emptyDataIconImageId)
        val textView = errorView.findViewById<View>(mStatusLayoutManager!!.emptyDataTextTipId)
        if (iconImageView != null && iconImageView is ImageView) {
            (iconImageView as ImageView).setImageResource(iconImage)
        }

        if (textView != null && textView is TextView) {
            (textView as TextView).text = textTip
        }
    }

    /**
     * 展示错误
     * @param objects
     */
    fun showLayoutError(vararg objects: Any) {
        if (inflateLayout(LAYOUT_ERROR_ID)) {
            showHideViewById(LAYOUT_ERROR_ID)

            val errorLayout = mStatusLayoutManager!!.errorLayout
            errorLayout?.setData(objects)
        }
    }

    /**
     * 根据ID显示隐藏布局
     * @param id                    id值
     */
    private fun showHideViewById(id: Int) {
        //这个需要遍历集合中数据，然后切换显示和隐藏
        for (i in 0 until mLayoutArray.size()) {
            val key = mLayoutArray.keyAt(i)
            val valueView = mLayoutArray.valueAt(i)
            //显示该view
            if (key == id) {
                //显示该视图
                valueView.setVisibility(View.VISIBLE)
                if (mStatusLayoutManager!!.onShowHideViewListener != null) {
                    mStatusLayoutManager!!.onShowHideViewListener!!.onViewShow(valueView, key)
                }
            } else {
                //隐藏该视图
                if (valueView.getVisibility() != View.GONE) {
                    valueView.setVisibility(View.GONE)
                    if (mStatusLayoutManager!!.onShowHideViewListener != null) {
                        mStatusLayoutManager!!.onShowHideViewListener!!.onViewHide(valueView, key)
                    }
                }
            }
        }
    }


    /**
     * 主要是显示ViewStub布局，比如网络异常，加载异常以及空数据等页面
     * 注意该方法中只有当切换到这些页面的时候，才会将ViewStub视图给inflate出来，之后才会走视图绘制的三大流程
     * 方法里面通过id判断来执行不同的代码，首先判断ViewStub是否为空，如果为空就代表没有添加这个View就返回false，
     * 不为空就加载View并且添加到集合当中，然后调用showHideViewById方法显示隐藏View，
     * retryLoad方法是给重试按钮添加事件
     * @param id                        布局id
     * @return                          是否inflate出视图
     */
    private fun inflateLayout(id: Int): Boolean {
        var isShow = true
        if (mLayoutArray.get(id) != null) {
            return isShow
        }
        when (id) {
            //网络异常
            LAYOUT_NETWORK_ERROR_ID -> if (mStatusLayoutManager!!.netWorkErrorVs != null) {
                //只有当展示的时候，才将ViewStub视图给inflate出来
                val view = mStatusLayoutManager!!.netWorkErrorVs!!.inflate()
                view.setOnClickListener {
                    Log.i("重试加载", "网络异常")
                    mStatusLayoutManager!!.onNetworkListener!!.onNetwork()
                }
                mLayoutArray.put(id, view)
                isShow = true
            } else {
                isShow = false
            }
            //加载异常
            LAYOUT_ERROR_ID -> if (mStatusLayoutManager!!.errorVs != null) {
                //只有当展示的时候，才将ViewStub视图给inflate出来
                val view = mStatusLayoutManager!!.errorVs!!.inflate()
                if (mStatusLayoutManager!!.errorLayout != null) {
                    mStatusLayoutManager!!.errorLayout!!.setView(view)
                }
                view.setOnClickListener { mStatusLayoutManager!!.onRetryListener!!.onRetry() }
                mLayoutArray.put(id, view)
                isShow = true
            } else {
                isShow = false
            }
            //空数据
            LAYOUT_EMPTY_DATA_ID -> if (mStatusLayoutManager!!.emptyDataVs != null) {
                //只有当展示的时候，才将ViewStub视图给inflate出来
                val view = mStatusLayoutManager!!.emptyDataVs!!.inflate()
                if (mStatusLayoutManager!!.emptyDataLayout != null) {
                    mStatusLayoutManager!!.emptyDataLayout!!.setView(view)
                }
                view.setOnClickListener { mStatusLayoutManager!!.onRetryListener!!.onRetry() }
                mLayoutArray.put(id, view)
                isShow = true
            } else {
                isShow = false
            }
            else -> {
            }
        }
        return isShow
    }

}