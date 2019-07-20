package com.link.component_menu.data

import com.link.component_menu.data.source.http.IHttpDataSource
import com.link.component_menu.data.source.local.ILocalDataSource
import com.link.librarymodule.base.mvvm.model.BaseModel

class MenuRepository private constructor(
        private val httpDataSource: IHttpDataSource,
        private val localDataSource: ILocalDataSource
) :
        BaseModel(), ILocalDataSource, IHttpDataSource {


    companion object {

        @Volatile
        private var instance: MenuRepository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILocalDataSource) =
                instance ?: synchronized(this) {
                    instance ?: MenuRepository(httpDataSource, localDataSource).also {
                        instance = it
                    }
                }
    }


}