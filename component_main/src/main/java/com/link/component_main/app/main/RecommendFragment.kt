package com.link.component_main.app.main

import android.os.Bundle
import android.view.LayoutInflater
import com.link.component_main.MainViewModelFactory
import com.link.component_main.R
import com.link.component_main.app.catalog.EmptyViewModel
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.ItemDecoration
import kotlinx.android.synthetic.main.main_fragment_recommend.*

class RecommendFragment(override var mLayoutId: Int = R.layout.main_fragment_recommend) : BaseMvvmFragment<EmptyViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() =
                RecommendFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }


    override fun initViewModel(): EmptyViewModel {
        return MainViewModelFactory.getInstance().create(EmptyViewModel::class.java)
    }

    private lateinit var mAdapter: RecommendAdapter

    override fun initView() {
        super.initView()

        val list = arrayListOf<String>()
        for (index in 0 until 10) {
            list.add("ele:$index")
        }

        mAdapter = RecommendAdapter(R.layout.main_item_recommend, list)

        val headView = LayoutInflater.from(context).inflate(R.layout.main_item_recommend_head, null)

        mAdapter.addHeaderView(headView)

        val headView2 = LayoutInflater.from(context).inflate(R.layout.main_item_recommend_head2, null)

        mAdapter.addHeaderView(headView2)

        rv_list.adapter = mAdapter

        rv_list.addItemDecoration(ItemDecoration(0,8,0,8))

    }
}

