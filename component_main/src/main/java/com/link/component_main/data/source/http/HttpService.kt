package com.link.component_main.data.source.http

import com.link.component_main.data.entity.CategoryResult
import com.link.component_main.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.constant.Constant
import io.reactivex.Observable
import retrofit2.http.*
import java.util.*

interface HttpService {

    /**
     * 分类标签列表
     * parentid：分类ID，默认全部
     */
    @FormUrlEncoded
    @Headers("${Constant.URL_TYPE}:${Constant.JUHE}")
    @POST("cook/category")
    fun category(
            @Field("key") key: String,
            @Field("parentid") parentid: String
    ): Observable<BaseEntity<CategoryResult>>


    /**
     * 菜谱大全
     * menu:菜谱的关键词
     */
    @FormUrlEncoded
    @Headers("${Constant.URL_TYPE}:${Constant.JUHE}")
    @POST("cook/query.php")
    fun query(
            @Field("key") key: String,
            @Field("menu") menu: String,
            @Field("pn") pn: Int,
            @Field("rn") rn: Int
    ): Observable<BaseEntity<MenuResult>>


    /**
     * 按标签检索菜谱
     * cid:category接口返回的id
     * pn:起始下标
     * rn:最大数量
     * format:steps字段屏蔽，默认显示，format=1时屏蔽
     */
    @FormUrlEncoded
    @Headers("${Constant.URL_TYPE}:${Constant.JUHE}")
    @POST("cook/index")
    fun index(
            @Field("key") key: String,
            @Field("cid") cid: String,
            @Field("pn") pn: Int,
            @Field("rn") rn: Int,
            @Field("format") format: Int
    ): Observable<BaseEntity<MenuResult>>


    /**
     * 按标签检索菜谱
     * id:菜谱的详细ID
     */
    @FormUrlEncoded
    @Headers("${Constant.URL_TYPE}:${Constant.JUHE}")
    @POST("cook/queryid")
    fun queryid(
            @Field("key") key: String,
            @Field("id") id: String
    ): Observable<BaseEntity<MenuResult>>


    /**
     * 获取app首页的轮播图数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/banner")
    fun banner(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取app首页的今日推荐数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/today")
    fun today(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取app首页的更多推荐数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/more")
    fun more(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取app首页的早餐推荐数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/breakfast")
    fun breakfast(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取app首页的午餐推荐数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/launch")
    fun launch(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取app首页的晚餐推荐数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/dinner")
    fun dinner(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取app首页的创意推荐数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/motion")
    fun motion(): Observable<BaseEntity<MenuResult>>

    /**
     * 获取app 发现页面的数据
     */
    @Headers("${Constant.URL_TYPE}:${Constant.MOCK}")
    @GET("api/find")
    fun find(): Observable<BaseEntity<MenuResult>>


}