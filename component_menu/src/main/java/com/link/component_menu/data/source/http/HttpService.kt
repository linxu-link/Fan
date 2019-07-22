package com.link.component_menu.data.source.http

import com.link.component_menu.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface HttpService {

    /**
     * 菜谱大全
     * menu:菜谱的关键词
     */
    @FormUrlEncoded
    @POST("cook/query.php")
    fun query(
            @Field("key") key: String,
            @Field("menu") menu: String,
            @Field("pn") pn: Int,
            @Field("rn") rn: Int
    ): Observable<BaseEntity<MenuResult>>


}