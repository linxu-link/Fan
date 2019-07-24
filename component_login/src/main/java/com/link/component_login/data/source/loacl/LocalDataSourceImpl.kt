package com.link.component_login.data.source.loacl

import com.link.librarycomponent.entity.user.UserEntity
import com.tencent.mmkv.MMKV

class LocalDataSourceImpl : ILocalDataSource {
    override fun saveUserInfo(phone: String, password: String) {
        MMKV.defaultMMKV().encode("phone", phone)
        MMKV.defaultMMKV().encode("password", password)
    }

    override fun getUserInfo(): UserEntity {
        val userEntity = UserEntity()
        userEntity.mobilePhoneNumber = MMKV.defaultMMKV().getString("phone", null)
        userEntity.pwd = MMKV.defaultMMKV().getString("password", null)
        return userEntity
    }
}