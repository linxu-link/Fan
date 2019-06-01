package com.link.component_login.data.source.loacl

import com.link.librarycomponent.app.user.UserEntity

interface ILocalDataSource {

    /**
     * 保存用户信息
     */
    fun saveUserInfo()

    /**
     * 获取用户信息
     */
    fun getUserInfo():UserEntity

}