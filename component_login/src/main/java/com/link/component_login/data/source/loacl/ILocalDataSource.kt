package com.link.component_login.data.source.loacl

import com.link.librarycomponent.entity.user.UserEntity

interface ILocalDataSource {

    /**
     * 保存用户信息
     */
    fun saveUserInfo(phone: String, password: String)

    /**
     * 获取用户信息
     */
    fun getUserInfo(): UserEntity

}