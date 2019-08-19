package com.link.component_search.data.source.http


import com.link.component_search.data.entity.CategoryResult
import com.link.component_search.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.constant.Constant
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
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


}