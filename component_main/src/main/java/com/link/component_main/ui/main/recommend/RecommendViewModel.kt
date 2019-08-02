package com.link.component_main.ui.main.recommend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_main.data.MainRepository
import com.link.component_main.data.entity.MenuDetail
import com.link.component_main.data.entity.MenuResult
import com.link.component_main.data.entity.Recommend
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils


class RecommendViewModel(repository: MainRepository) : BaseViewModel<MainRepository>(repository) {


    var bannerData = MutableLiveData<List<MenuDetail>>()
    var todayData = MutableLiveData<List<MenuDetail>>()
    var moreData = MutableLiveData<List<MenuDetail>>()

    var otherData = MutableLiveData<List<MenuDetail>>()

    init {

    }


    fun getRecommendData() {

        val query = BmobQuery<Recommend>()
        query.addQueryKeys("banner,today,more")
        query.findObjects(object : FindListener<Recommend>() {

            override fun done(list: List<Recommend>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.isNotEmpty()) {
                        val banner = Gson().fromJson<BaseEntity<MenuResult>>(list[0].banner.toString(), object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        bannerData.value = banner.result.data

                        val today = Gson().fromJson<BaseEntity<MenuResult>>(list[0].today.toString(), object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        todayData.value = today.result.data

                        val more = Gson().fromJson<BaseEntity<MenuResult>>(list[0].more.toString(), object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        moreData.value = more.result.data

                    }
                } else {
                    Log.e("error", e.toString())
                    ToastUtils.showLong(e.toString())
                }
            }

        })

    }

    fun getData(index: Int) {

        val query = BmobQuery<Recommend>()

        if (index == 1) {
            query.addQueryKeys("breakfast")
        } else if (index == 2) {
            query.addQueryKeys("lunch")
        } else if (index == 3) {
            query.addQueryKeys("dinner")
        }else if(index==4){
            query.addQueryKeys("motion")
        }
        query.findObjects(object : FindListener<Recommend>() {

            override fun done(list: List<Recommend>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.isNotEmpty()) {

                        var json = ""

                        if (index == 1) {
                            json = list[0].breakfast.toString()
                        } else if (index == 2) {
                            json = list[0].lunch.toString()
                        } else if (index == 3) {
                            json = list[0].dinner.toString()
                        }else if (index==4){
                            json=list[0].motion.toString()
                        }

                        val result = Gson().fromJson<BaseEntity<MenuResult>>(json, object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        otherData.value = result.result.data


                    }
                } else {
                    Log.e("error", e.toString())
                    ToastUtils.showLong(e.toString())
                }
            }

        })

    }


}