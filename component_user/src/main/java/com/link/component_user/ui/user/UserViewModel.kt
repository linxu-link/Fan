package com.link.component_user.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobUser
import com.link.component_user.data.UserRepository
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FetchUserInfoListener
import com.link.librarymodule.utils.ToastUtils


class UserViewModel(repository: UserRepository) : BaseViewModel<UserRepository>(repository) {


    val userEntity = MutableLiveData<UserEntity>()

    init {
        userEntity.value = BmobUser.getCurrentUser(UserEntity::class.java)
    }

    fun getUserData() {
        BmobUser.fetchUserInfo(object : FetchUserInfoListener<BmobUser>() {
            override fun done(user: BmobUser, e: BmobException?) {
                if (e == null) {
                    userEntity.value = BmobUser.getCurrentUser(UserEntity::class.java)
                } else {
                    ToastUtils.showLong(e.message)
                    Log.e("error", e.message)
                }
            }
        })
    }


}