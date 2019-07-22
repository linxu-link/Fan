package com.link.component_search.app.search

import androidx.lifecycle.MutableLiveData
import com.link.component_search.data.SearchRepository
import com.link.component_search.data.entity.MenuDetail
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel constructor(repository: SearchRepository) : BaseViewModel<SearchRepository>(repository) {

    val searchWord = MutableLiveData<String>()

    val searchData = MutableLiveData<List<MenuDetail>>()

    fun search(menu: String, pn: Int, rn: Int) {
        addSubscribe(model.search(menu, pn, rn)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.resultcode == "200") {
                        searchData.value = it.result.data
                    } else {
                        ToastUtils.showLong(it.reason)
                    }
                })
    }

}