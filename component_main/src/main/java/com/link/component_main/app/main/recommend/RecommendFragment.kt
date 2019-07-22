package com.link.component_main.app.main.recommend

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.link.component_main.R
import com.link.component_main.app.MainViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.widgets.HorizontalBar
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.main_fragment_recommend.*

/**
 * @author WJ
 * @date 2019-07-18
 *
 * 描述：首页的推荐页
 */

const val INDEX = "index"

class RecommendFragment(override var layoutId: Int = R.layout.main_fragment_recommend) : BaseMvvmFragment<RecommendViewModel>() {

    private var index: Int = 0

    companion object {
        @JvmStatic
        fun newInstance(index: Int) =
                RecommendFragment().apply {
                    arguments = Bundle().apply {
                        putInt(INDEX, index)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            index = arguments!!.getInt(INDEX, 0)
        }
    }


    override fun initViewModel(): RecommendViewModel {
        return MainViewModelFactory.getInstance().create(RecommendViewModel::class.java)
    }

    private lateinit var mAdapter: RecommendHeadAdapter
    private lateinit var mHeadAdapter: RecommendHeadAdapter
    private lateinit var mHead2Adapter: RecommendHeadAdapter


    override fun initView() {
        super.initView()

        refresh.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorPrimary))
        refresh.setOnRefreshListener {
            getData()
        }

        mAdapter = RecommendHeadAdapter(R.layout.main_item_recommend, null)

        if (index == 0) {

            mViewModel.moreData.observe(this, Observer {
                mAdapter.setNewData(it)
                refresh.isRefreshing = false
            })


            initHeaderView()
            initHeaderView2()
        } else {

            mViewModel.otherData.observe(this, Observer {
                mAdapter.setNewData(it)
                refresh.isRefreshing = false
            })

        }

        rv_list.adapter = mAdapter
        rv_list.addItemDecoration(ItemDecoration(0, 8, 0, 8))

        mAdapter.setOnItemClickListener { adapter, view, position ->

            val data = Gson().toJson(mAdapter.getItem(position))

            ARouter.getInstance().build(RouterConstant.MENU)
                    .withString("MenuDetail",data)
                    .navigation()

        }

    }

    private fun initHeaderView() {

        mHeadAdapter = RecommendHeadAdapter(R.layout.main_item_recommend_head_item, null)

        val headView = LayoutInflater.from(context).inflate(R.layout.main_recommend_head, null)
        val rvHead = headView.findViewById<RecyclerView>(R.id.rv_head)
        val line = headView.findViewById<HorizontalBar>(R.id.line)

        mViewModel.bannerData.observe(this, Observer {
            mHeadAdapter.setNewData(it)
            line.mMaxNum = mViewModel.bannerData.value!!.size
        })
        line.mMaxNum = 1

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

        mHead2Adapter = RecommendHeadAdapter(R.layout.main_item_recommend_head_item2, null)

        mViewModel.todayData.observe(this, Observer {
            mHead2Adapter.setNewData(it)
        })

        val headView2 = LayoutInflater.from(context).inflate(R.layout.main_recommend_head2, null)
        val rvHead = headView2.findViewById<RecyclerView>(R.id.rv_head2)
        rvHead.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvHead.adapter = mHead2Adapter
        rvHead.addItemDecoration(ItemDecoration(0, 0, 16, 0))
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

    override fun getData() {
        super.getData()
        refresh.isRefreshing = true
        if (index == 0) {
            mViewModel.getRecommendData()
        } else {
            mViewModel.getData(index)
        }
    }


}

