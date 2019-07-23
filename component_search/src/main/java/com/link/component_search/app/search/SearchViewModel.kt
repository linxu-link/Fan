package com.link.component_search.app.search

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_search.data.SearchRepository
import com.link.component_search.data.entity.MenuDetail
import com.link.component_search.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.base.mvvm.livedata.SingleLiveEvent
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class SearchViewModel constructor(repository: SearchRepository) : BaseViewModel<SearchRepository>(repository) {

    val searchWord = MutableLiveData<String>()
    val searchId = MutableLiveData<String>()

    val searchData = MutableLiveData<List<MenuDetail>>()

    val searchHistory = MutableLiveData<List<String>>()

    val uc = UIChangeObservable()

    inner class UIChangeObservable() {
        val jump = SingleLiveEvent<Boolean>()
    }


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

    fun getSearchData() {
        addSubscribe(model.getCatalogData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    val data = Gson().fromJson<BaseEntity<MenuResult>>(it, object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                    searchData.value = data.result.data
                }))

    }

    fun index(pn: Int, rn: Int) {
        addSubscribe(model.index(searchId.value!!, pn, rn)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    if (it.resultcode == "200") {
                        searchData.value = it.result.data
                    } else {
                        ToastUtils.showLong(it.reason)
                    }
                }, Consumer {
                    ToastUtils.showLong(it.toString())
                }))

    }

    fun getHistory() {


    }

}