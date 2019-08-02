package com.link.component_login.data

import com.link.component_login.data.source.http.IHttpDataSource
import com.link.component_login.data.source.loacl.ILocalDataSource
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.model.BaseRepository

class LoginRepository private constructor(
        val httpDataSource: IHttpDataSource,
        val localDataSource: ILocalDataSource
) :
        BaseRepository(), IHttpDataSource, ILocalDataSource {


    companion object {

        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILocalDataSource) =
                instance ?: synchronized(this) {
                    instance
                            ?: LoginRepository(httpDataSource, localDataSource).also {
                                instance = it
                            }
                }
    }

    override fun saveUserInfo(phone: String, password: String) {
        return localDataSource.saveUserInfo(phone, password)
    }

    override fun getUserInfo(): UserEntity {
        return localDataSource.getUserInfo()
    }

}