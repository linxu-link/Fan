package com.link.component_shopping.data.source.http

import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable


interface IHttpDataSource {

    fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>>

    fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>>

}