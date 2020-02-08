package com.link.fan.app.search

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.link.fan.data.bean.HistoryEntity
import com.link.fan.data.repository.AppRepository
import com.link.librarymodule.utils.executors.AppExecutors
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class SearchViewModel(private val appRepository: AppRepository) : ViewModel() {

    val searchWord = MutableLiveData<String>()
    val searchHistory = MutableLiveData<List<HistoryEntity>>()

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

    fun setSearchWord(word: String) {
        searchWord.value = word
    }

}