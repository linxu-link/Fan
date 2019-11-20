package com.link.fan.app.main.mine


import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.link.fan.R
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.utils.CommonUtil

/**
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-10:55
 *  email:wujia0916@thundersoft.com
 *  description:
 */
class MineFragment(override var layoutId: Int = R.layout.fragment_mine) : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (CommonUtil.getStatusBarHeight() >= 0) {
            val layout = FrameLayout.LayoutParams(mRootView!!.layoutParams)
            layout.setMargins(0, CommonUtil.getStatusBarHeight(), 0, 0)
            mRootView!!.layoutParams = layout
        }

    }


    companion object {

        @JvmStatic
        fun newInstance() =
                MineFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
