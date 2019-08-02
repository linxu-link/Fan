package com.link.component_user.data

import com.link.component_user.data.source.http.IHttpDataSource
import com.link.component_user.data.source.local.ILocalDataSource
import com.link.librarymodule.base.mvvm.model.BaseRepository

class UserRepository private constructor(
        private val httpDataSource: IHttpDataSource,
        private val localDataSource: ILocalDataSource
) :
        BaseRepository(), ILocalDataSource, IHttpDataSource {


    companion object {

        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILocalDataSource) =
                instance ?: synchronized(this) {
                    instance ?: UserRepository(httpDataSource, localDataSource).also {
                        instance = it
                    }
                }
    }

}