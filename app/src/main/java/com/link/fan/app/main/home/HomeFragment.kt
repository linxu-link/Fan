package com.link.fan.app.main.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import com.link.fan.R
import com.link.librarymodule.base.BaseFragment
import com.link.librarymodule.utils.CommonUtil

/**
 * <pre>
 *  copyright:TS
 *  author:wujia
 *  create:2019-10-26-11:03
 *  email:wujia0916@thundersoft.com
 *  description:
 * <pre>
 */
class HomeFragment(override var layoutId: Int = R.layout.fragment_home) : BaseFragment() {

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
            mRootView!!.layoutParams=layout
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        //                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
