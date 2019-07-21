package com.link.component_login.app.register

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.link.component_login.data.LoginRepository
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel


class RegisterViewModel(repository: LoginRepository) : BaseViewModel<LoginRepository>(repository) {

    //用户名的绑定
    val phone = MutableLiveData<String>()
    //密码的绑定
    val password = MutableLiveData<String>()

    fun signUp() {

        val user = UserEntity()
        user.username = phone.value
        user.setPassword(password.value)
        user.signUp(object : SaveListener<UserEntity>() {
            override fun done(user: UserEntity, e: BmobException?) {
                if (e == null) {

                } else {

                }
            }
        })

    }

}