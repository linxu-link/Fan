package com.link.librarymodule.base.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

const val TAG = "PagingViewModel"

abstract class PagingViewModel<T> : BaseViewModel() {

    var mPageData: LiveData<PagedList<T>>
    var mBoundaryPageData: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var mDataSource: DataSource<Any?, T>

    protected var mConfig: PagedList.Config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(12)
            .build()

    private val mFactory = object : DataSource.Factory<Any?, T>() {

        override fun create(): DataSource<Any?, T> {
            Log.e(TAG, "create")
            mDataSource = createDataSource() as DataSource<Any?, T>
            return mDataSource
        }

    }

    private val mCallBack = object : PagedList.BoundaryCallback<T>() {

        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            // 新提交的PagedList中没有数据
            mBoundaryPageData.value = false
        }

        override fun onItemAtEndLoaded(itemAtEnd: T) {
            super.onItemAtEndLoaded(itemAtEnd)
            // 新提交的PagedList中第一条数据被加载到列表上
            mBoundaryPageData.value = true
        }

        override fun onItemAtFrontLoaded(itemAtFront: T) {
            super.onItemAtFrontLoaded(itemAtFront)
            // 新提交的PagedList中最后一条数据被加载到列表上
        }

    }


    init {
        Log.e(TAG, "init")
        mPageData = LivePagedListBuilder(mFactory, mConfig)
                .setInitialLoadKey(0)
                .setBoundaryCallback(mCallBack)
                .build()
    }

    abstract fun createDataSource(): DataSource<*, T>

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

}