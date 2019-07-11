package com.link.component_main.data.source.local

import io.reactivex.Observable

interface ILocalDataSource {

    fun getCatalogData():Observable<String>

}