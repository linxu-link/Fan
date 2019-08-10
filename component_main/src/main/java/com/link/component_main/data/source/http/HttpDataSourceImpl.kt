package com.link.component_main.data.source.http

import com.link.component_main.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable


/**
 * @author WJ
 * @date 2019-05-30
 *
 * 描述：网络数据源
 */
class HttpDataSourceImpl constructor(private val service: HttpService) : IHttpDataSource {
    override fun breakfast(): Observable<BaseEntity<MenuResult>> {
        return service.breakfast()
    }

    override fun launch(): Observable<BaseEntity<MenuResult>> {
        return service.launch()
    }

    override fun dinner(): Observable<BaseEntity<MenuResult>> {
        return service.dinner()
    }

    override fun motion(): Observable<BaseEntity<MenuResult>> {
        return service.motion()
    }

    override fun find(): Observable<BaseEntity<MenuResult>> {
        return service.find()
    }

    override fun banner(): Observable<BaseEntity<MenuResult>> {
        return service.banner()
    }

    override fun more(): Observable<BaseEntity<MenuResult>> {
        return service.more()
    }

    override fun today(): Observable<BaseEntity<MenuResult>> {
        return service.today()
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