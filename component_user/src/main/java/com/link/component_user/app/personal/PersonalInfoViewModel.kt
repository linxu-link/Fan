package com.link.component_user.app.personal

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import com.link.component_user.data.UserRepository
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.bus.event.SingleLiveEvent
import com.link.librarymodule.utils.ToastUtils

class PersonalInfoViewModel(repository: UserRepository) : BaseViewModel<UserRepository>(repository) {


    val userEntity = MutableLiveData<UserEntity>()

    //封装一个界面发生改变的观察者
    var uc = UIChangeObservable()

    inner class UIChangeObservable {
        //注册成功的监听
        var pUpdateEvent = SingleLiveEvent<Boolean>()
    }

    init {
        userEntity.value = BmobUser.getCurrentUser(UserEntity::class.java)
    }

    fun updateUserInfo() {
        if (userEntity.value!!.displayName!!.isEmpty()) {
            ToastUtils.showShort("请输入您的昵称")
            return
        }

        userEntity.value!!.update(object : UpdateListener() {
            override fun done(e: BmobException?) {
                if (e == null) {
                    ToastUtils.showShort("更新成功")
                    uc.pUpdateEvent.value = true
                } else {
                    ToastUtils.showLong(e.toString())
                }
            }

        })

    }

    fun loginOut() {
        if (BmobUser.isLogin()) {
            BmobUser.logOut()
        }
    }

}