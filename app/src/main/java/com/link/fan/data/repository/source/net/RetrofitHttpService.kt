package com.link.fan.data.repository.source.net

import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.fan.data.bean.BaseEntity
import com.link.fan.data.bean.BaseResult
import com.link.fan.data.bean.CommunityEntity
import com.link.fan.data.bean.MenuResult
import com.link.librarymodule.constant.DEFAULT_URL
import com.link.librarymodule.constant.MOCK
import com.link.librarymodule.constant.URL_TYPE
import io.reactivex.Observable
import retrofit2.http.*

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-18:52
 * email:wujia0916@thundersoft.com
 * description:  定义网络数据源的操作
 */
interface RetrofitHttpService {

    /**
     * 获取首页的轮播图.
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5dd13d3a11f6e545761facb1/fan/home/banner")
    fun homeBanner(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的今日推荐.
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5dd13d3a11f6e545761facb1/fan/home/today")
    fun today(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的最新菜谱.
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5dd13d3a11f6e545761facb1/fan/home/last_menu")
    fun lastMenu(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的菜谱列表.
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5dd13d3a11f6e545761facb1/fan/home")
    fun home(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取商城的活动的数据
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5dd13d3a11f6e545761facb1/fan/mall/newGoods")
    fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>>


    /**
     * 获取商城的秒杀的数据
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5dd13d3a11f6e545761facb1/fan/mall/newSeconds")
    fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>>

    /**
     * 获取社区发帖列表
     */
    @Headers("${URL_TYPE}:${DEFAULT_URL}")
    @GET("serverdemo/feeds/queryHotFeedsList")
    fun getCommunityList(@Query("pageCount") pageCount: Int,
                         @Query("feedId") feedId: Int,
                         @Query("feedType") feedType: String,
                         @Query("userId") userId: Int): Observable<BaseEntity<BaseResult<List<CommunityEntity>>>>

}