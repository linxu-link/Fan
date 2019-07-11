package com.link.component_main.app.catalog

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_main.data.MainRepository
import com.link.component_main.data.entity.CategoryResult
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CatalogViewModel constructor(repository: MainRepository) :
        BaseViewModel<MainRepository>(repository) {


    var cataLog = MutableLiveData<List<CategoryResult>>()

    init {

    }


    fun getCatalogData() {

        addSubscribe(getModel().localDataSource.getCatalogData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<String>{
                    val data=Gson().fromJson<ArrayList<CategoryResult>>(it,object :TypeToken<ArrayList<CategoryResult>>(){}.type)
                    cataLog.value=data
                })
        )



    }

}