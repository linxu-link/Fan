package com.link.fan.app.community.list

import android.util.Log
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagedList
import com.link.fan.data.bean.CommunityEntity
import com.link.fan.data.repository.AppRepository
import com.link.librarymodule.base.mvvm.viewmodel.PagingViewModel
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.widgets.pading.MutablePageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

const val TAG = "CommunityListViewModel"

class CommunityListViewModel(val repository: AppRepository) : PagingViewModel<CommunityEntity>() {

    val mCommunityLiveData = MutableLiveData<PagedList<CommunityEntity>>()

    private val mLoadAfter: AtomicBoolean = AtomicBoolean(false)

    internal inner class CommunityDataSource : ItemKeyedDataSource<Int, CommunityEntity>() {

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<CommunityEntity>) {
            // 加载初始化数据的
            Log.e(TAG, "loadInitial")
            loadData(0, params.requestedLoadSize, callback)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<CommunityEntity>) {
            // 向后加载分页数据的
            Log.e(TAG, "loadAfter")
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<CommunityEntity>) {
            // 能够向前加载数据的
            callback.onResult(Collections.emptyList())
            Log.e(TAG, "loadBefore")
        }

        override fun getKey(item: CommunityEntity): Int {
            Log.e(TAG, "getKey")
            return item.id
        }

    }

    override fun createDataSource(): DataSource<*, CommunityEntity> {
        return CommunityDataSource()
    }

    fun loadData(key: Int, count: Int, callback: ItemKeyedDataSource.LoadCallback<CommunityEntity>) {
        if (key > 0) {
            mLoadAfter.set(true)
        }
        //TODO
        repository.getCommunityList(count, key, "all", 0).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    it?.run {
                        if (status == 200) {

                            val dataSource = MutablePageKeyedDataSource<CommunityEntity>()
                            dataSource.data.addAll(data.data)

                            val pagedList = dataSource.buildNewPagedList(mConfig)
                            mCommunityLiveData.value = pagedList

                        } else {
                            ToastUtils.showShort(message)
                        }
                    }
                }, Consumer {
                    it?.run {
                        ToastUtils.showShort(message)
                    }

                })

    }

    fun loadAfter(id: Int, callback: ItemKeyedDataSource.LoadCallback<CommunityEntity>) {
        if (mLoadAfter.get()) {
            callback.onResult(Collections.emptyList())
            return
        }
        ArchTaskExecutor.getIOThreadExecutor().execute(Runnable {
            loadData(id, mConfig.pageSize, callback)
        })
    }

}