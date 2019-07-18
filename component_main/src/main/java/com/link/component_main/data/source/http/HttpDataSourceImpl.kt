package com.link.component_main.data.source.http

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.link.component_main.data.entity.CategoryResult
import com.link.component_main.data.entity.MenuDetail
import com.link.component_main.data.entity.RecommendEntity
import io.reactivex.Observable


/**
 * @author WJ
 * @date 2019-05-30
 *
 * 描述：网络数据源
 */
class HttpDataSourceImpl constructor(private val service: HttpService) : IHttpDataSource {


    companion object {
        @Volatile
        private var instance: HttpDataSourceImpl? = null

        fun getInstance(service: HttpService) =
                instance ?: synchronized(this) {
                    instance ?: HttpDataSourceImpl(service).also { instance = it }
                }
    }


}