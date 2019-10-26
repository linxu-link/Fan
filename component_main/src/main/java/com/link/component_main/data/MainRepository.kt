package com.link.component_main.data

import com.link.component_main.data.entity.MenuResult
import com.link.component_main.data.source.http.IHttpDataSource
import com.link.component_main.data.source.local.ILocalDataSource
import com.link.librarycomponent.entity.base.BaseEntity
import com.link.librarymodule.base.mvvm.model.BaseRepository
import io.reactivex.Observable

class MainRepository private constructor(
        val httpDataSource: IHttpDataSource,
        val localDataSource: ILocalDataSource
) :
        BaseRepository(), ILocalDataSource, IHttpDataSource {

    override fun breakfast(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.breakfast()
    }

    override fun launch(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.launch()
    }

    override fun dinner(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.dinner()
    }

    override fun motion(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.motion()
    }

    override fun find(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.find()
    }

    override fun banner(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.banner()
    }

    override fun today(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.today()
    }

    override fun more(): Observable<BaseEntity<MenuResult>> {
        return httpDataSource.more()
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
}