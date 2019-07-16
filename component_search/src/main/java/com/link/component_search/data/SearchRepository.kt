package com.link.component_search.data

import com.link.component_search.data.source.http.IHttpDataSource
import com.link.component_search.data.source.local.ILocalDataSource
import com.link.librarymodule.base.mvvm.model.BaseModel
import io.reactivex.Observable

class SearchRepository private constructor(
        val httpDataSource: IHttpDataSource,
        val localDataSource: ILocalDataSource
) :
        BaseModel(), ILocalDataSource {


    companion object {

        @Volatile
        private var instance: SearchRepository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILocalDataSource) =
                instance ?: synchronized(this) {
                    instance ?: SearchRepository(httpDataSource, localDataSource).also {
                        instance = it
                    }
                }
    }


    /**
     * 获取本地分类数据
     */
    override fun getCatalogData(): Observable<String> {
        return localDataSource.getCatalogData()
    }

}