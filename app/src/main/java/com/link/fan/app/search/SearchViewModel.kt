package com.link.fan.app.search

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.link.fan.data.bean.MenuResult
import com.link.fan.data.repository.AppRepository
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.utils.executors.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val appRepository: AppRepository) : ViewModel() {

    val searchWord = MutableLiveData<String>()
    val searchId = MutableLiveData<String>()
    val searchData = MutableLiveData<MenuResult>()
    val searchHistory = MutableLiveData<List<HistoryEntity>>()

    val enableLoadMore = MutableLiveData<Boolean>()

    fun searchByKeyword(pn: Int, rn: Int) {
        searchWord.value?.let { word ->
            appRepository.searchByKeyword(word, pn, rn)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.status == 200) {
                            searchData.value = it.data
                            enableLoadMore.value = it.data.data.size == 10
                        } else {
                            ToastUtils.showLong(it.message)
                            enableLoadMore.value = false
                        }
                    }, {
                        ToastUtils.showLong(it.toString())
                    })
        }
    }

    fun searchByIndex(pn: Int, rn: Int) {
        searchId.value?.let { id ->
            appRepository.searchByIndex(id, pn, rn)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe({
                        if (it.status == 200) {
                            searchData.value = it.data
                            enableLoadMore.value = it.data.data.size == 10
                        } else {
                            enableLoadMore.value = false
                            ToastUtils.showLong(it.message)
                        }
                    }, {
                        ToastUtils.showLong(it.toString())
                    })
        }
    }

    fun getSearchHistoryData() {

        appRepository.getSearchHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    searchHistory.value = it
                })

    }

    fun insertSearchWord(searchWord: String) {
        val executor = AppExecutors()
        executor.diskIO().execute(Runnable {
            val search = HistoryEntity()
            search.content = searchWord
            try {
                appRepository.insertSearchData(search)
            } catch (e: SQLiteConstraintException) {
                e.printStackTrace()
            }
        })


    }

    fun clearSearchHistory() {
        searchHistory.value?.let {
            val executor = AppExecutors()
            executor.diskIO().execute(Runnable {
                appRepository.clearSearchHistory(it)
            })
        }
    }

}