package com.link.component_login.data

import com.link.component_splash.data.source.http.IHttpDataSource
import com.link.component_splash.data.source.local.ILoaclDataSource
import com.link.librarymodule.base.mvvm.model.BaseModel

class Repository private constructor(
    val httpDataSource: IHttpDataSource,
    val localDataSource: ILoaclDataSource?
) :
    BaseModel() {

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILoaclDataSource?) =
            instance ?: synchronized(this) {
                instance ?: Repository(httpDataSource, localDataSource).also {
                    instance = it
                }
            }
    }

}