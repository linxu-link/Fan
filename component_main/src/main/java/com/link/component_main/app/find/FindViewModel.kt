package com.link.component_main.app.find

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_main.data.MainRepository
import com.link.component_main.data.entity.Find
import com.link.component_main.data.entity.MenuDetail
import com.link.component_main.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils

class FindViewModel(repository: MainRepository) : BaseViewModel<MainRepository>(repository) {

    val data = MutableLiveData<List<MenuDetail>>()

    fun getData() {
        val query = BmobQuery<Find>()
        query.addQueryKeys("find")
        query.findObjects(object : FindListener<Find>() {

            override fun done(list: List<Find>?, e: BmobException?) {
                if (e == null) {
                    if (list != null && list.isNotEmpty()) {
                        val result = Gson().fromJson<BaseEntity<MenuResult>>(list[0].find.toString(), object : TypeToken<BaseEntity<MenuResult>>() {}.type)
                        data.value = result.result.data
                    }
                } else {
                    Log.e("error", e.toString())
                    ToastUtils.showLong(e.toString())
                }
            }

        })
    }


}