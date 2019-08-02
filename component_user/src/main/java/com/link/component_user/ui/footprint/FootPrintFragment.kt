package com.link.component_user.ui.footprint


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

import com.link.component_user.R
import com.link.component_user.ui.ViewModelFactory
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.user_fragment_foot_print.*

/**
 * @author WJ
 * @date 2019-07-21
 *
 * 描述：我的足迹
 */
class FootPrintFragment(override var layoutId: Int = R.layout.user_fragment_foot_print)
    : BaseMvvmFragment<FootPrintViewModel>() {


    override fun getViewModel(): FootPrintViewModel {
        return ViewModelFactory.getInstance().create(FootPrintViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FootPrintFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    private lateinit var mAdapter: FootPrintAdapter

    override fun initView() {
        super.initView()
        refresh.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorPrimary))
        refresh.setOnRefreshListener {
            getData()
        }
        val title = mRootView!!.findViewById<TextView>(R.id.title)
        val back = mRootView!!.findViewById<ImageView>(R.id.back)
        title.text = "我的足迹"
        back.setOnClickListener {
            activity!!.onBackPressed()
        }

        mAdapter = FootPrintAdapter(R.layout.user_item_foot_print, null)
        rv_list.adapter = mAdapter
        rv_list.addItemDecoration(ItemDecoration(0, 10, 10, 0))
    }

    override fun initViewObservable() {
        super.initViewObservable()
        mViewModel.mFootPrintData.observe(this, Observer {
            mAdapter.setNewData(it)
            refresh.isRefreshing = false
        })

    }

    override fun getData() {
        super.getData()
        refresh.isRefreshing = true
        mViewModel.getFootPrintData()
    }

}
