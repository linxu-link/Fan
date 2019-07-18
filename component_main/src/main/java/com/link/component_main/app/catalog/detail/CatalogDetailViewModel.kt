package com.link.component_main.app.catalog.detail

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_main.data.MainRepository
import com.link.component_main.data.entity.CategoryResult
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class CatalogDetailViewModel constructor(repository: MainRepository) :
        BaseViewModel<MainRepository>(repository) {

    var cataLog = MutableLiveData<List<CategoryResult>>()
    var ingredients = MutableLiveData<List<CategoryResult>>()

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

    fun getIngredients(){
        addSubscribe(getModel().localDataSource.getIngredientsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<String>{
                    val data=Gson().fromJson<ArrayList<CategoryResult>>(it,object :TypeToken<ArrayList<CategoryResult>>(){}.type)
                    ingredients.value=data
                })
        )
    }

}