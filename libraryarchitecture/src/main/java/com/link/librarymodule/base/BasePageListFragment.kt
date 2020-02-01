package com.link.librarymodule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.link.librarymodule.R
import com.link.librarymodule.base.mvvm.viewmodel.PagingViewModel
import com.link.librarymodule.databinding.LayoutRefreshRecyclerviewBinding

abstract class BasePageListFragment<T, M : PagingViewModel<T>> : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    protected lateinit var mViewModel: M

    protected lateinit var mAdapter: PagedListAdapter<T, out RecyclerView.ViewHolder>

    protected lateinit var mBinding: LayoutRefreshRecyclerviewBinding

    protected lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = LayoutRefreshRecyclerviewBinding.inflate(inflater, container, false)
        mBinding.root.fitsSystemWindows = true
        mBinding.refresh.setOnRefreshListener(this)

        mAdapter = createPagedListAdapter()
        mBinding.recyclerView.adapter = mAdapter
        mRecyclerView = mBinding.recyclerView

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.list_divider_2px)!!)
        mRecyclerView.addItemDecoration(dividerItemDecoration)

        mViewModel = createViewModel()
        initViewModel()

        return mBinding.root
    }

    open fun initViewModel() {

        // 触发页面初始化数据加载的逻辑
        mViewModel.mPageData.observe(this, Observer {
            submitList(it)
        })

        // 监听分页时有无更多数据,以决定是否关闭上拉加载的动画
        mViewModel.mBoundaryPageData.observe(this, Observer { hasData ->
            finishRefresh(hasData)
        })
    }

    fun submitList(pagedList: PagedList<T>) {
        if (pagedList.size > 0) {
            mAdapter.submitList(pagedList)
        }
        finishRefresh(pagedList.size > 0)
    }

    open fun finishRefresh(hasData: Boolean) {
        // TODO

    }

    // 创建当前页面的ViewModel,因为ViewModel的创建可能需要传入不同的数据源，所以ViewModel交给子类实例化
    abstract fun createViewModel(): M

    // 创建当前页面的PagedListAdapter
    abstract fun createPagedListAdapter(): PagedListAdapter<T, out RecyclerView.ViewHolder>

}