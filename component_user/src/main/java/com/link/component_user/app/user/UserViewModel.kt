package com.link.component_user.app.user

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobUser
import com.link.component_user.data.UserRepository
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel

class UserViewModel(repository: UserRepository):BaseViewModel<UserRepository>(repository) {


    val userEntity=MutableLiveData<UserEntity>()

    init {
        userEntity.value=BmobUser.getCurrentUser(UserEntity::class.java)
    }



}