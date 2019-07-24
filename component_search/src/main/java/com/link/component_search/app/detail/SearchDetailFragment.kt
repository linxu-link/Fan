package com.link.component_search.app.detail

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.link.component_search.R
import com.link.component_search.app.SearchViewModelFactory
import com.link.component_search.app.search.SearchViewModel
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.mvvm.view.BaseMvvmFragment
import com.link.librarymodule.constant.Constant
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.widgets.recyclerview.ItemDecoration
import kotlinx.android.synthetic.main.search_fragment_search.*

/**
 * @author WJ
 * @date 2019-07-16
 *
 * 描述：
 */
class SearchDetailFragment(override var layoutId: Int = R.layout.search_fragment_search) : BaseMvvmFragment<SearchViewModel>() {

    override fun getViewModel(): SearchViewModel {
        return ViewModelProviders.of(activity!!, SearchViewModelFactory.getInstance()).get(SearchViewModel::class.java)
    }

    private lateinit var mAdapter: SearchDetailAdapter

    companion object {
        @JvmStatic
        fun newInstance(id: String?, name: String) =
                SearchDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(Constant.ID, id)
                        putString(Constant.NAME, name)
                    }
                }
    }


    override fun initParam() {
        super.initParam()
        arguments?.let {
            val searchWord = it.getString(Constant.NAME)
            val searchId = it.getString(Constant.ID)
            if (searchId != "---") {
                mViewModel.searchId.value = searchId
            } else {
                mViewModel.searchWord.value = searchWord
            }
        }

    }


    override fun initView() {
        super.initView()
        mAdapter = SearchDetailAdapter(R.layout.search_item_detail, null)
        rvList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvList.addItemDecoration(ItemDecoration(5, 5, 5, 5))
        rvList.adapter = mAdapter
        if (mViewModel.searchData.value != null) {
            mAdapter.setNewData(mViewModel.searchData.value!!.data)
        }
        mAdapter.setOnItemClickListener { _, _, position ->
            ARouter.getInstance()
                    .build(RouterConstant.MENU)
                    .withString("MenuDetail", Gson().toJson(mAdapter.getItem(position)))
                    .navigation()
        }
        btn_search.setOnClickListener {
            val menu: String = et_search.text.toString()
            if (menu.isNotEmpty()) {
                mViewModel.searchWord.value = menu
            }
        }

        mAdapter.setOnLoadMoreListener({
            if (mViewModel.searchId.value != null) {
                //在上一次数据
                mViewModel.index(mViewModel.searchData.value!!.pn.toInt() + 10, 10)
            } else {
                mViewModel.search(mViewModel.searchData.value!!.pn.toInt() + 10, 10)
            }
        }, rvList)

    }


    override fun initViewObservable() {
        super.initViewObservable()

        mViewModel.searchData.observe(this, Observer {
            if (it.pn == "0") {
                mAdapter.setNewData(it.data)
            } else {
                mAdapter.addData(it.data)
            }
            mAdapter.loadMoreComplete()
            if (it.data.isEmpty()) {
                mAdapter.loadMoreEnd(true)
            }
            mAdapter.setEnableLoadMore(it.data.size <= 10)
        })

        mViewModel.searchWord.observe(this, Observer {
            et_search.setText(it)
            mViewModel.search(0, 10)
        })

        mViewModel.searchId.observe(this, Observer {
            mViewModel.index(0, 10)
        })

    }

    override fun getData() {
        super.getData()

    }


}