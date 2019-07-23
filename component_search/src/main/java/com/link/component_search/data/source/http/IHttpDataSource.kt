package com.link.component_search.data.source.http

import com.link.component_search.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable

interface IHttpDataSource {

    //根据关键词检索菜谱列表
    fun search(menu: String, pn: Int, rn: Int): Observable<BaseEntity<MenuResult>>

    //按标签检索菜谱
    fun index(cid: String, pn: Int, rn: Int): Observable<BaseEntity<MenuResult>>

}