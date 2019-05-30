package com.link.component_splash.data

import com.link.component_splash.data.source.http.IHttpDataSource
import com.link.component_splash.data.source.local.ILoaclDataSource
import com.link.librarymodule.base.mvvm.model.BaseModel

class SplashRepository private constructor(
    val httpDataSource: IHttpDataSource,
    val localDataSource: ILoaclDataSource?
) :
    BaseModel() {

    companion object {

        @Volatile
        private var instance: SplashRepository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILoaclDataSource?) =
            instance ?: synchronized(this) {
                instance ?: SplashRepository(httpDataSource, localDataSource).also {
                    instance = it
                }
            }
    }

}