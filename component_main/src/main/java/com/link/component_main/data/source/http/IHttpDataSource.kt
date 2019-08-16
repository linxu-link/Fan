package com.link.component_main.data.source.http

import com.link.component_main.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable

interface IHttpDataSource {


    fun banner(): Observable<BaseEntity<MenuResult>>
    fun today(): Observable<BaseEntity<MenuResult>>
    fun more(): Observable<BaseEntity<MenuResult>>

    fun breakfast(): Observable<BaseEntity<MenuResult>>
    fun launch(): Observable<BaseEntity<MenuResult>>
    fun dinner(): Observable<BaseEntity<MenuResult>>
    fun motion(): Observable<BaseEntity<MenuResult>>

    fun find(): Observable<BaseEntity<MenuResult>>


}