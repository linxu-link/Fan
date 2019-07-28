package com.link.component_search.app.search

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import com.link.component_search.data.SearchRepository
import com.link.component_search.data.entity.HistoryEntity
import com.link.component_search.data.entity.MenuResult
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.utils.executors.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class SearchViewModel constructor(repository: SearchRepository) : BaseViewModel<SearchRepository>(repository) {

    val searchWord = MutableLiveData<String>()
    val searchId = MutableLiveData<String>()
    val searchData = MutableLiveData<MenuResult>()
    val searchHistory = MutableLiveData<List<HistoryEntity>>()

    val enableLoadMore = MutableLiveData<Boolean>()

    fun search(pn: Int, rn: Int) {
        addSubscribe(model.search(searchWord.value!!, pn, rn)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.resultcode == "200") {
                        searchData.value = it.result
                        enableLoadMore.value = it.result.data.size == 10
                    } else {
                        ToastUtils.showLong(it.reason)
                        enableLoadMore.value = false
                    }
                })
    }

    fun index(pn: Int, rn: Int) {
        addSubscribe(model.index(searchId.value!!, pn, rn)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    if (it.resultcode == "200") {
                        searchData.value = it.result
                        enableLoadMore.value = it.result.data.size == 10
                    } else {
                        enableLoadMore.value = false
                        ToastUtils.showLong(it.reason)
                    }
                }, {
                    ToastUtils.showLong(it.toString())
                }))

    }

    fun getSearchHistoryData() {
        addSubscribe(model.getSearchHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    searchHistory.value = it
                }))

    }

    fun insertSearchWord(searchWord: String) {

        val executor = AppExecutors()
        executor.diskIO().execute(Runnable {
            val search = HistoryEntity()
            search.content = searchWord
            try {
                getModel().insertSearchData(search)
            } catch (e: SQLiteConstraintException) {
                e.printStackTrace()
            }
        })


    }

    fun clearSearchHistory() {
        if (searchHistory.value != null) {
            val executor = AppExecutors()
            executor.diskIO().execute(Runnable {
                getModel().clearSearchHistory(searchHistory.value!!)
            })
        }
    }

}