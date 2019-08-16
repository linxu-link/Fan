package com.link.component_shopping.data

import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.component_shopping.data.source.http.IHttpDataSource
import com.link.component_shopping.data.source.local.ILocalDataSource
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.base.mvvm.model.BaseRepository
import io.reactivex.Observable


class ShoppingRepository private constructor(
        private val httpDataSource: IHttpDataSource,
        private val localDataSource: ILocalDataSource
) :
        BaseRepository(), ILocalDataSource, IHttpDataSource {


    override fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>> {
        return httpDataSource.getGoods()
    }

    override fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>>{
        return httpDataSource.getSeconds()
    }


    companion object {

        @Volatile
        private var instance: ShoppingRepository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILocalDataSource) =
                instance ?: synchronized(this) {
                    instance ?: ShoppingRepository(httpDataSource, localDataSource).also {
                        instance = it
                    }
                }
    }


}