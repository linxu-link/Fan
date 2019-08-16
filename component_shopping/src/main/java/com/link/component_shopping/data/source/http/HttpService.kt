package com.link.component_shopping.data.source.http

import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.constant.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


interface HttpService {


    /**
     * 获取商城的活动的数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/newGoods")
    fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>>


    /**
     * 获取商城的活动的数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/newSeconds")
    fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>>


}