package com.link.component_main.app.main.recommend

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
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.link.librarymodule.utils.ToastUtils
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class RecommendViewModel(repository: MainRepository) : BaseViewModel<MainRepository>(repository) {


    var mBanner = MutableLiveData<List<MenuDetail>>()
    var mToday = MutableLiveData<List<MenuDetail>>()
    var mMore = MutableLiveData<List<MenuDetail>>()

    init {

    }


    fun getData() {

        val query = BmobQuery<Recommend>()
        query.addQueryKeys("banner,today,more")
        query.findObjects(object : FindListener<Recommend>() {

            override fun done(list: List<Recommend>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.isNotEmpty()) {
                        val banner = Gson().fromJson<BaseEntity<MenuResult>>(list[0].banner.toString(), object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        mBanner.value = banner.result.data

                        val today = Gson().fromJson<BaseEntity<MenuResult>>(list[0].today.toString(), object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        mToday.value = today.result.data

                        val more = Gson().fromJson<BaseEntity<MenuResult>>(list[0].more.toString(), object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        mMore.value = more.result.data

                    }
                } else {
                    Log.e("error", e.toString())
                    ToastUtils.showLong(e.toString())
                }
            }

        })

    }


}