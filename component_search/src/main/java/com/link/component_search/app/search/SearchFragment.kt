package com.link.component_search.app.search

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.link.component_search.R
import com.link.component_search.app.SearchViewModelFactory
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.utils.ToastUtils
import kotlinx.android.synthetic.main.search_fragment_search.*

/**
 * @author WJ
 * @date 2019-07-16
 *
 * 描述：搜索界面
 */
class SearchFragment(override var layoutId: Int = R.layout.search_fragment_search) : BaseMvvmFragment<SearchViewModel>() {

    override fun getViewModel(): SearchViewModel {
        return ViewModelProviders.of(activity!!, SearchViewModelFactory.getInstance()).get(SearchViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                SearchFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    private lateinit var mAdapter: SearchAdapter

    override fun initView() {
        super.initView()

        mAdapter = SearchAdapter(android.R.layout.simple_list_item_activated_1, null)
        rvList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvList.adapter = mAdapter

        initHeaderView()

        btn_search.setOnClickListener {
            val menu: String = et_search.text.toString()
            if (menu.isNotEmpty()) {
                mViewModel.searchWord.value = menu
                Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
            }
        }

        mAdapter.setOnItemClickListener { _, _, position ->
            mViewModel.searchWord.value = mAdapter.getItem(position)!!.content
            Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
        }


    }

    private fun initHeaderView() {
        val header = LayoutInflater.from(context).inflate(R.layout.search_head_view, null)
        header.findViewById<FrameLayout>(R.id.search_recommend_1).setOnClickListener {
            mViewModel.searchWord.value = "宫保鸡丁"
            Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_2).setOnClickListener {
            mViewModel.searchWord.value = "糖醋排骨"
            Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_3).setOnClickListener {
            mViewModel.searchWord.value = "红烧肉"
            Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_4).setOnClickListener {
            mViewModel.searchWord.value = "水煮肉片"
            Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_5).setOnClickListener {
            mViewModel.searchWord.value = "麻婆豆腐"
            Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
        }
        header.findViewById<FrameLayout>(R.id.search_recommend_6).setOnClickListener {
            mViewModel.searchWord.value = "可乐鸡翅"
            Navigation.findNavController(activity!!, R.id.root_view).navigate(R.id.action_searchfragment_to_searchdetailfragment)
        }

        header.findViewById<TextView>(R.id.btn_clear).setOnClickListener {
            mViewModel.clearSearchHistory()
        }

        mAdapter.addHeaderView(header)
    }

    override fun getData() {
        super.getData()
        mViewModel.getSearchHistoryData()
    }

    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel.searchHistory.observe(this, Observer {
            mAdapter.setNewData(it)
        })

        mViewModel.searchWord.observe(this, Observer {
            mViewModel.insertSearchWord(it)
        })

    }

}