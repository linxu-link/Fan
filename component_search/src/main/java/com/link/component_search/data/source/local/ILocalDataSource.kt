package com.link.component_search.data.source.local

import io.reactivex.Observable

interface ILocalDataSource {

    fun getCatalogData():Observable<String>

}