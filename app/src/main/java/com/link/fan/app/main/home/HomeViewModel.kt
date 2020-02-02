package com.link.fan.app.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.link.fan.data.bean.MenuResult
import com.link.fan.data.repository.AppRepository
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-06-17:08
 * email:wujia0916@thundersoft.com
 * description:
 */
class HomeViewModel constructor(private val repository: AppRepository) : ViewModel() {

    val masterLiveData = MutableLiveData<MenuResult>()
    val recommendLiveData = MutableLiveData<MenuResult>()
    val lastMenuLiveData = MutableLiveData<MenuResult>()
    val bannerLiveData = MutableLiveData<MenuResult>()

    val keywords = MutableLiveData<String>("请输入关键词")

    val msgCount = MutableLiveData<Int>(10)

    fun requestData() {

        repository.getHomeDataList().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    it?.run {
                        if (status == 200) {
                            masterLiveData.value = data
                        } else {
                            ToastUtils.showShort(message)
                        }
                    }
                }, Consumer {
                    it?.run {
                        ToastUtils.showShort(message)
                    }

                })


        repository.getTodayRecommend().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    it?.run {
                        if (status == 200) {
                            recommendLiveData.value = data
                        } else {
                            ToastUtils.showShort(message)
                        }
                    }
                }, Consumer {
                    it?.run {
                        ToastUtils.showShort(message)
                    }

                })

        repository.getLastMenu().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    it?.run {
                        if (status == 200) {
                            lastMenuLiveData.value = data
                        } else {
                            ToastUtils.showShort(message)
                        }
                    }
                }, Consumer {
                    it?.run {
                        ToastUtils.showShort(message)
                    }

                })

        repository.getHomeBanner().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(Consumer {
                    it?.run {
                        if (status == 200) {
                            bannerLiveData.value = data
                        } else {
                            ToastUtils.showShort(message)
                        }
                    }
                }, Consumer {
                    it?.run {
                        ToastUtils.showShort(message)
                    }

                })


    }

}