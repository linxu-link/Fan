package com.link.fan.data.repository.source.net

import com.link.component_shopping.data.entity.EntityResult
import com.link.component_shopping.data.entity.GoodsEntity
import com.link.component_shopping.data.entity.SecondsEntity
import com.link.fan.data.bean.*
import com.link.librarymodule.constant.DEFAULT_URL
import com.link.librarymodule.constant.JUHE
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
    @GET("mock/5e3648f7b9de7529052c4d3b/shandao/home/banner")
    fun getHomeBanner(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的今日推荐.
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5e3648f7b9de7529052c4d3b/shandao/home/today")
    fun getTodayRecommend(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的最新菜谱.
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5e3648f7b9de7529052c4d3b/shandao/home/last_menu")
    fun getLastMenu(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取首页的菜谱列表.
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5e3648f7b9de7529052c4d3b/shandao/home")
    fun getHomeDataList(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取商城的活动的数据
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5e3648f7b9de7529052c4d3b/shandao/mall/newGoods")
    fun getGoods(): Observable<BaseEntity<EntityResult<List<GoodsEntity>>>>


    /**
     * 获取商城的秒杀的数据
     */
    @Headers("${URL_TYPE}:${MOCK}")
    @GET("mock/5e3648f7b9de7529052c4d3b/shandao/mall/newSeconds")
    fun getSeconds(): Observable<BaseEntity<EntityResult<List<SecondsEntity>>>>

    /**
     * 获取社区发帖列表
     */
    @Headers("${URL_TYPE}:${DEFAULT_URL}")
    @GET("serverdemo/feeds/queryHotFeedsList")
    fun getCommunityList(
            @Query("pageCount") pageCount: Int,
            @Query("feedId") feedId: Int,
            @Query("feedType") feedType: String,
            @Query("userId") userId: Int
    ): Observable<BaseEntity<BaseResult<List<CommunityEntity>>>>

    /**
     * 菜谱大全
     * menu:菜谱的关键词
     */
    @FormUrlEncoded
    @Headers("${URL_TYPE}:${JUHE}")
    @POST("recipe/search")
    fun searchByKeyword(
            @Field("keyword") keyword: String,
            @Field("num") num: Int,
            @Field("start") start: Int,
            @Field("appkey") appkey: String
    ): Observable<BaseEntity<BaseResult<List<JuHeMenuResult>>>>

    /**
     * 分类标签列表
     * parentid：分类ID，默认全部
     */
    @FormUrlEncoded
    @Headers("${URL_TYPE}:${JUHE}")
    @POST("recipe/class")
    fun category(
            @Field("appkey") appkey: String
    ): Observable<BaseEntity<CategoryResult>>

    /**
     * 按标签检索菜谱
     * cid:category接口返回的id
     * pn:起始下标
     * rn:最大数量
     * format:steps字段屏蔽，默认显示，format=1时屏蔽
     */
    @FormUrlEncoded
    @Headers("${URL_TYPE}:${JUHE}")
    @POST("recipe/byclass")
    fun searchByCategory(
            @Field("appkey") appkey: String,
            @Field("classid") classId: Int,
            @Field("start") start: Int,
            @Field("num") num: Int
    ): Observable<BaseEntity<BaseResult<List<JuHeMenuResult>>>>

    /**
     * 按标签检索菜谱
     * id:菜谱的详细ID
     */
    @FormUrlEncoded
    @Headers("${URL_TYPE}:${JUHE}")
    @POST("recipe/detail")
    fun searchById(
            @Field("appkey") appkey: String,
            @Field("id") id: Int
    ): Observable<BaseEntity<BaseResult<List<JuHeMenuResult>>>>

}