package com.link.fan.app.login

import com.link.librarymodule.base.mvvm.model.BaseRepository

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-01-10:48
 * email:wujia0916@thundersoft.com
 * description:
 */
class LoginRepository : BaseRepository() {

    fun login(phone: String, code: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getPhoneCode() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: LoginRepository().also {
                        instance = it
                    }
                }
    }

}