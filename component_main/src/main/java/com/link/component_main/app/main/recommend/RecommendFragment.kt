package com.link.component_main.app.main.recommend

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.link.component_main.MainViewModelFactory
import com.link.component_main.app.catalog.EmptyViewModel
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.HorizontalBar
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.main_fragment_recommend.*


class RecommendFragment(override var layoutId: Int = com.link.component_main.R.layout.main_fragment_recommend) : BaseMvvmFragment<EmptyViewModel>() {

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
    private lateinit var mHeadAdapter: RecommendHeadAdapter
    private lateinit var mHead2Adapter: RecommendHeadAdapter
    private val mCache = RecyclerView.RecycledViewPool()


    override fun initView() {
        super.initView()
        val list = arrayListOf<String>()
        for (index in 0 until 10) {
            list.add("ele:$index")
        }

        mAdapter = RecommendAdapter(com.link.component_main.R.layout.main_item_recommend, list)

        initHeaderView()
        initHeaderView2()

        rv_list.adapter = mAdapter
        rv_list.addItemDecoration(ItemDecoration(0, 8, 0, 8))
        rv_list.setRecycledViewPool(mCache)

    }

    private fun initHeaderView() {

        val list = arrayListOf<String>()
        for (index in 0 until 5) {
            list.add("ele:$index")
        }

        mHeadAdapter = RecommendHeadAdapter(com.link.component_main.R.layout.main_item_recommend_head_item, list)


        val headView = LayoutInflater.from(context).inflate(com.link.component_main.R.layout.main_item_recommend_head, null)
        val rvHead = headView.findViewById<RecyclerView>(com.link.component_main.R.id.rv_head)
        val line = headView.findViewById<HorizontalBar>(com.link.component_main.R.id.line)

        line.mMaxNum = list.size

        rvHead.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvHead.adapter = mHeadAdapter
        rvHead.addItemDecoration(ItemDecoration(6, 0, 6, 0))
        val helper = PagerSnapHelper()
        helper.attachToRecyclerView(rvHead)
        mAdapter.addHeaderView(headView)


        line.mCurrentNum = 1f
        rvHead.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                line.mCurrentNum = getPositionAndOffset(rvHead).toFloat() + 1
            }
        })
    }

    private fun initHeaderView2() {

        val list = arrayListOf<String>()
        for (index in 0 until 10) {
            list.add("ele:$index")
        }
        mHead2Adapter = RecommendHeadAdapter(com.link.component_main.R.layout.main_item_recommend_head_item2, list)

        val headView2 = LayoutInflater.from(context).inflate(com.link.component_main.R.layout.main_item_recommend_head2, null)
        val rvHead = headView2.findViewById<RecyclerView>(com.link.component_main.R.id.rv_head2)
        rvHead.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvHead.adapter = mHead2Adapter
        rvHead.addItemDecoration(ItemDecoration(6, 0, 6, 0))
        mAdapter.addHeaderView(headView2)

    }


    private fun getPositionAndOffset(rv: RecyclerView): Int {
        val layoutManager = rv.layoutManager as LinearLayoutManager
        //获取可视的第一个view
        val topView = layoutManager.getChildAt(0)
        if (topView != null) {
            //得到该View的数组位置
            return layoutManager.getPosition(topView)
        }
        return 0
    }


}

