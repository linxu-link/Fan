package com.link.component_main.app.main.recommend

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_main.data.MainRepository
import com.link.component_main.data.entity.MenuDetail
import com.link.component_main.data.entity.MenuResult
import com.link.component_main.data.entity.RecommendEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils

class RecommendViewModel(repository: MainRepository) : BaseViewModel<MainRepository>(repository) {


    var liveData = MutableLiveData<MutableList<MenuDetail>>()

    init {

    }


    fun getData() {

        val query = BmobQuery<RecommendEntity>()
        query.addQueryKeys("banner,today,more")
        query.findObjects(object : FindListener<RecommendEntity>() {

            override fun done(list: MutableList<RecommendEntity>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.size > 0) {
                        val data = Gson().fromJson<MutableList<MenuResult>>(list[0].banner, object : TypeToken<MutableList<MenuResult>>() {}.type)
//                        liveData.value = data
                    }
                } else {
                    ToastUtils.showLong(e.toString())
                }
            }

        })

    }


}