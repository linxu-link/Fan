package com.link.component_search.app.search

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_search.data.SearchRepository
import com.link.component_search.data.entity.HistoryEntity
import com.link.component_search.data.entity.MenuDetail
import com.link.component_search.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.base.mvvm.livedata.SingleLiveEvent
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

    fun search(pn: Int, rn: Int) {
        addSubscribe(model.search(searchWord.value!!, pn, rn)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.resultcode == "200") {
                        searchData.value = it.result
                    } else {
                        ToastUtils.showLong(it.reason)
                    }
                })
    }

    fun index(pn: Int, rn: Int) {
        addSubscribe(model.index(searchId.value!!, pn, rn)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    if (it.resultcode == "200") {
                        searchData.value = it.result
                    } else {
                        ToastUtils.showLong(it.reason)
                    }
                }, Consumer {
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