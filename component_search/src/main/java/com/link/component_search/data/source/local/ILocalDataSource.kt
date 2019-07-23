package com.link.component_search.data.source.local

import com.link.component_search.data.entity.MenuResult
import com.link.librarycomponent.entity.base.BaseEntity
import io.reactivex.Observable

interface ILocalDataSource {

    fun getCatalogData(): Observable<String>

}