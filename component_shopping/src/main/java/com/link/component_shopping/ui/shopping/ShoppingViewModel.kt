package com.link.component_shopping.ui.shopping

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.link.component_shopping.data.ShoppingRepository
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ShoppingViewModel(repository: ShoppingRepository) : BaseViewModel<ShoppingRepository>(repository) {

    var goodsData = MutableLiveData<String>()

    var secondData = MutableLiveData<String>()

    /**
     * 获取远程数据
     */
    fun getRemoteData() {

        addSubscribe(Observable.merge(model.getGoods(), model.getSeconds())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    if (it.resultcode == "200") {

                        if (it.result.data.isNullOrEmpty()){
                            return@Consumer
                        }

                        if (it.result.data[0] is GoodsEntity) {
                            goodsData.value = Gson().toJson(it.result.data)


                        } else if (it.result.data[0] is SecondsEntity) {
                            secondData.value=Gson().toJson(it.result.data)
                        }
                    }
                })
        )

    }


}