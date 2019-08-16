package com.link.component_shopping.data.source.http

import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable


/**
 * @author WJ
 * @date 2019-05-30
 *
 * 描述：网络数据源
 */
class HttpDataSourceImpl constructor(private val service: HttpService) : IHttpDataSource {

    override fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>> {
        return service.getGoods()
    }

    override fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>> {
        return service.getSeconds()
    }


    companion object {
        @Volatile
        private var instance: HttpDataSourceImpl? = null

        fun getInstance(service: HttpService) =
                instance ?: synchronized(this) {
                    instance ?: HttpDataSourceImpl(service).also { instance = it }
                }
    }


}