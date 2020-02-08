package com.link.fan.app.search.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.link.fan.data.bean.JuHeMenuResult
import com.link.fan.data.repository.AppRepository
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchDetailViewModel(private val mAppRepository: AppRepository) : ViewModel() {

    val mSearchData = MutableLiveData<List<JuHeMenuResult>>()

    fun searchByKeyword(searchWord: String, start: Int, num: Int) {
        mAppRepository.searchByKeyword(searchWord, num, start, "")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.status == 0) {
                        mSearchData.value = it.result.list
                    } else {
                        ToastUtils.showLong(it.msg)
                    }
                }, {
                    ToastUtils.showLong(it.toString())
                })
    }

    fun searchById(searchId: String, start: Int, num: Int) {
        mAppRepository.searchByCategory("", searchId.toInt(), start, num)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    if (it.status == 0) {
                        mSearchData.value = it.result.list
                    } else {
                        ToastUtils.showLong(it.msg)
                    }
                }, {
                    ToastUtils.showLong(it.toString())
                })
    }


}