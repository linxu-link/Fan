package com.link.component_main.data

import com.link.component_main.data.source.http.IHttpDataSource
import com.link.component_main.data.source.local.ILocalDataSource
import com.link.librarymodule.base.mvvm.model.BaseRepository
import io.reactivex.Observable

class MainRepository private constructor(
        val httpDataSource: IHttpDataSource,
        val localDataSource: ILocalDataSource
) :
        BaseRepository(), ILocalDataSource {



    companion object {

        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILocalDataSource) =
                instance ?: synchronized(this) {
                    instance ?: MainRepository(httpDataSource, localDataSource).also {
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

    override fun getIngredientsData(): Observable<String> {
        return localDataSource.getIngredientsData()
    }
}