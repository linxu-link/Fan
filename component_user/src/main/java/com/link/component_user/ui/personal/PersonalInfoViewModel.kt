package com.link.component_user.ui.personal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FetchUserInfoListener
import cn.bmob.v3.listener.UpdateListener
import cn.bmob.v3.listener.UploadFileListener
import com.link.component_user.data.UserRepository
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.livedata.SingleLiveEvent
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.utils.ToastUtils
import java.io.File

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
                    updateUserData()
                } else {
                    ToastUtils.showLong(e.toString())
                }
            }

        })
    }

    private fun updateUserData() {
        BmobUser.fetchUserInfo(object : FetchUserInfoListener<BmobUser>() {
            override fun done(user: BmobUser, e: BmobException?) {
                if (e == null) {
                    ToastUtils.showShort("更新成功")
                    userEntity.value = BmobUser.getCurrentUser(UserEntity::class.java)
                    uc.pUpdateEvent.value = true
                } else {
                    ToastUtils.showLong(e.message)
                    Log.e("error", e.message)
                }
            }
        })
    }

    fun updateAvatar(path: String) {
        if (path.isEmpty()) {
            return
        }

        val bmobFile = BmobFile(File(path))

        bmobFile.uploadblock(object : UploadFileListener() {
            override fun done(e: BmobException?) {
                if (e == null) {
                    ToastUtils.showLong("上传成功")
                    userEntity.value!!.avatar = bmobFile
                } else {
                    ToastUtils.showLong(e.message)
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