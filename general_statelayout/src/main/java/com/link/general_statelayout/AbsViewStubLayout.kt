package com.link.general_statelayout

import android.content.Context
import android.view.View
import android.view.ViewStub
import androidx.annotation.LayoutRes

abstract class AbsViewStubLayout {

    /**
     * ViewStub用来加载网络异常，空数据等页面
     */
    private var mLayoutVs: ViewStub? = null
    /**
     * View用来加载正常视图页面
     */
    private var mContentView: View? = null

    fun initLayout(context: Context, @LayoutRes layoutResId: Int) {
        mLayoutVs = ViewStub(context)
        mLayoutVs!!.layoutResource = layoutResId
    }

    fun getLayoutVs(): ViewStub? {
        return mLayoutVs
    }

    fun setView(contentView: View) {
        mContentView = contentView
    }

    /**
     * 设置数据
     * @param objects           数据
     */
     abstract fun setData(vararg objects: Any)

}