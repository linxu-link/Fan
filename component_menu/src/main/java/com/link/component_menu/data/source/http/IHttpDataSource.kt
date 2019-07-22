package com.link.component_menu.data.source.http

import com.link.component_menu.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable

interface IHttpDataSource {

    fun getRecommend(menu: String, pn: Int, rn: Int): Observable<BaseEntity<MenuResult>>

}