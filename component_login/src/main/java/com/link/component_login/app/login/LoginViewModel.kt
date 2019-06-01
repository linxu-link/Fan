package com.link.component_login.app.login

import android.app.Application
import android.text.TextUtils
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.link.component_login.data.Repository
import com.link.librarymodule.base.mvvm.binding.command.BindingAction
import com.link.librarymodule.base.mvvm.binding.command.BindingCommand
import com.link.librarymodule.base.mvvm.binding.command.BindingConsumer
import com.link.librarymodule.base.mvvm.viewmodel.BaseViewModel
import com.link.librarymodule.bus.event.SingleLiveEvent
import com.link.librarymodule.utils.ToastUtils

class LoginViewModel constructor(application: Application, model: Repository) :
    BaseViewModel<Repository>(application, model) {

    //用户名的绑定
    val phone = ObservableField("")
    //密码的绑定
    val password = ObservableField("")
    //用户名清除按钮的显示隐藏绑定
    val clearBtnVisibility = ObservableInt()

    //封装一个界面发生改变的观察者
    var uc = UIChangeObservable()

    inner class UIChangeObservable {
        //密码开关观察者
        var pSwitchEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    }

    init {
        phone.set(getModel().getUserInfo().mobilePhoneNumber)
        password.set(getModel().getUserInfo().pwd)
    }

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    val clearPhoneOnClickCommand = BindingCommand<Void>(object : BindingAction {
        override fun call() {
            phone.set("")
        }
    })

    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    var passwordShowSwitchOnClickCommand = BindingCommand<Void>(object : BindingAction {
        override fun call() {
            //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
            uc.pSwitchEvent.value = uc.pSwitchEvent.value == null || !uc.pSwitchEvent.value!!
        }
    })
    //用户名输入框焦点改变的回调事件
    var onFocusChangeCommand = BindingCommand<Void>(object : BindingConsumer<Boolean> {
        override fun call(t: Boolean) {
            if (t){
                clearBtnVisibility.set(View.VISIBLE)
            }else{
                clearBtnVisibility.set(View.INVISIBLE)
            }
        }

    })

    //登录按钮的点击事件
    var loginOnClickCommand = BindingCommand<Void>(object : BindingAction {
        override fun call() {
            login()
        }
    })

    /**
     * 登录操作
     */
    fun login() {
        if (TextUtils.isEmpty(phone.get())) {
            ToastUtils.showShort("请输入手机号码！")
            return
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！")
            return
        }

    }


}