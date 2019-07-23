package com.link.component_login.app.register

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.SaveListener
import com.link.component_login.data.LoginRepository
import com.link.librarycomponent.entity.user.UserEntity
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.bus.event.SingleLiveEvent
import com.link.librarymodule.utils.ToastUtils


class RegisterViewModel(repository: LoginRepository) : BaseViewModel<LoginRepository>(repository) {

    //用户名的绑定
    val phone = MutableLiveData<String>()
    //密码的绑定
    val password = MutableLiveData<String>()

    val code = MutableLiveData<String>()

    //封装一个界面发生改变的观察者
    var uc = UIChangeObservable()

    inner class UIChangeObservable {
        //密码开关观察者
        var pSwitchEvent = SingleLiveEvent<Boolean>()

        //注册成功的监听
        var pRegisterEvent = SingleLiveEvent<Boolean>()
    }


    init {

        phone.value = null
        password.value = null
        code.value = null
        uc.pSwitchEvent.value = false

    }


    fun signUp() {
        if (!check()) {
            return
        }

        val userEntity = UserEntity()
        userEntity.displayName = "请设定昵称"
        userEntity.username = phone.value
        userEntity.setPassword(password.value)
        userEntity.mobilePhoneNumber = phone.value

        userEntity.signOrLogin(code.value, object : SaveListener<UserEntity>() {
            override fun done(user: UserEntity?, e: BmobException?) {
                if (e == null) {
                    ToastUtils.showShort("注册成功")
                    uc.pRegisterEvent.value = true
                } else {
                    ToastUtils.showLong(e.toString())
                    Log.e("error", e.toString());
                }

            }

        })
    }

    //请求验证码
    fun requestSMSCode() {
        if (TextUtils.isEmpty(phone.value)) {
            ToastUtils.showShort("请输入验证码")
            return
        }

        BmobSMS.requestSMSCode(phone.value, "即刻美食", object : QueryListener<Int>() {
            override fun done(smsId: Int?, e: BmobException?) {
                if (e == null) {
                    ToastUtils.showShort("发送成功")
                } else {
                    ToastUtils.showLong(e.toString())
                    Log.e("error", e.toString());
                }
            }
        })

    }


    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    fun clearPhone() {
        phone.value = null
    }

    private fun check(): Boolean {

        if (TextUtils.isEmpty(phone.value)) {
            ToastUtils.showShort("请输入手机号码！")
            return false
        }
        if (TextUtils.isEmpty(password.value)) {
            ToastUtils.showShort("请输入密码！")
            return false
        }
        if (TextUtils.isEmpty(code.value)) {
            ToastUtils.showShort("请输入验证码")
            return false
        }

        return true

    }


}