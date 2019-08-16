package com.link.component_main.ui.find

import androidx.lifecycle.MutableLiveData
import com.link.component_main.data.MainRepository
import com.link.component_main.data.entity.MenuDetail
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FindViewModel(repository: MainRepository) : BaseViewModel<MainRepository>(repository) {

    val data = MutableLiveData<List<MenuDetail>>()

    fun getData() {
        addSubscribe(model.find()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ data ->
                    if (data.resultcode == "200") {
                        this.data.value = data.result.data
                    }
                }, { t -> ToastUtils.showShort(t.toString()) })
        )
    }


}