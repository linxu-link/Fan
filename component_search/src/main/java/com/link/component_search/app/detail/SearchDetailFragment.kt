package com.link.component_search.app.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.link.component_search.R
import com.link.component_search.app.SearchViewModelFactory
import com.link.component_search.app.search.SearchViewModel
import com.link.librarymodule.base.BaseFragment
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

    override fun initViewModel(): SearchViewModel {
        return ViewModelProviders.of(activity!!, SearchViewModelFactory.getInstance()).get(SearchViewModel::class.java)
    }

    private lateinit var mAdapter: SearchDetailAdapter

    companion object {
        @JvmStatic
        fun newInstance(id: String?) =
                SearchDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(id, Constant.ID)
                    }
                }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = SearchDetailAdapter(R.layout.search_item_detail, null)
        rvList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvList.addItemDecoration(ItemDecoration(5, 5, 5, 5))
        rvList.adapter = mAdapter

        mAdapter.setNewData(mViewModel.searchData.value)

        mViewModel.searchData.observe(this, Observer {
            mAdapter.setNewData(it)
        })


//        ToastUtils.showLong(mViewModel.searchData.value!!.size)

    }

}