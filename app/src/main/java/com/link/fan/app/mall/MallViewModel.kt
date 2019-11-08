package com.link.fan.app.mall

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.fan.data.repository.AppRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MallViewModel constructor(private val repository: AppRepository) : ViewModel() {


    var goodsData = MutableLiveData<String>()

    var secondData = MutableLiveData<String>()

    /**
     * 获取远程数据
     */
    fun getRemoteData() {

        Observable.merge(repository.getGoods(), repository.getSeconds())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    if (it.resultcode == "200") {

                        if (it.result.data.isNullOrEmpty()) {
                            return@Consumer
                        }

                        it.result.data?.let {
                            if (it[0] is GoodsEntity) {
                                goodsData.value = Gson().toJson(it.result.data)


                            } else if (it[0] is SecondsEntity) {
                                secondData.value = Gson().toJson(it.result.data)
                            }
                        }

                    }
                }, Consumer {

                })

    }

}