package com.link.component_login.data

import com.link.component_login.data.source.http.IHttpDataSource
import com.link.component_login.data.source.loacl.ILocalDataSource
import com.link.librarycomponent.app.user.UserEntity
import com.link.librarymodule.base.mvvm.model.BaseModel

class Repository private constructor(
    val httpDataSource: IHttpDataSource,
    val localDataSource: ILocalDataSource?
) :
    BaseModel() ,IHttpDataSource,ILocalDataSource{

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(httpDataSource: IHttpDataSource, localDataSource: ILocalDataSource?) =
            instance ?: synchronized(this) {
                instance
                    ?: Repository(httpDataSource, localDataSource).also {
                    instance = it
                }
            }
    }

    override fun saveUserInfo() {

    }

    override fun getUserInfo():UserEntity {
       return UserEntity()
    }

}