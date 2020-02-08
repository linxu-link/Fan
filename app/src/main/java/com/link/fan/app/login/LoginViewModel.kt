package com.link.fan.app.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
//import cn.bmob.v3.BmobUser
//import cn.bmob.v3.exception.BmobException
//import cn.bmob.v3.listener.QueryListener
//import cn.bmob.v3.listener.SaveListener
import com.link.fan.data.repository.AppRepository
import com.link.librarymodule.utils.RxCountDown
import com.link.librarymodule.utils.ToastUtils
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


/**
 * copyright:TS
 * author:wujia
 * create:2019-11-01-10:48
 * email:wujia0916@thundersoft.com
 * description: login viewModel.
 * 登录的所有操作都定义在ILoginPresenter中，ILoginPresenter不是必须的，直接在ViewModel中写也是可以的
 */
class LoginViewModel constructor(private var repository: AppRepository) : ViewModel(), ILoginPresenter {

    var phoneNumber = MutableLiveData<String>("")
    var phoneCode = MutableLiveData<String>("")
    var sendBtnEnable = MutableLiveData<Boolean>(true)
    var codeBtnText = MutableLiveData<String>("获取验证码")

    override fun login() {

        if (phoneCode.value.isNullOrEmpty()) {
            ToastUtils.showShort("请输入验证码")
            return
        }

        if (phoneNumber.value.isNullOrEmpty()) {
            ToastUtils.showShort("请输入手机号码")
            return
        }

//        repository.login(phoneNumber.value!!, phoneCode.value!!, object : SaveListener<BmobUser>() {
//            override fun done(p0: BmobUser?, p1: BmobException?) {
//                if (p1 != null) {
//                    ToastUtils.showShort(p1.message)
//                } else {
//                    ToastUtils.showShort("登录注册成功")
//                }
//            }
//        })

    }

    private var mDisposable: Disposable? = null

    override fun clickSmsCode() {
        if (sendBtnEnable.value!!) {

            if (phoneNumber.value.isNullOrEmpty()) {
                ToastUtils.showShort("请输入手机号码")
                return
            }

            sendBtnEnable.value = false

//            repository.getSmsCode(phoneNumber.value!!, object : QueryListener<Int>() {
//                override fun done(p0: Int?, p1: BmobException?) {
//                    if (p1 != null) {
//                        ToastUtils.showShort(p1.message)
//                    } else {
//                        mDisposable = RxCountDown.countdown(60)
//                                .subscribe(Consumer {
//                                    if (it == 0) {
//                                        codeBtnText.value = "获取验证码"
//                                        sendBtnEnable.value = true
//                                    } else {
//                                        codeBtnText.value = "已发送剩余${it}秒"
//                                    }
//                                })
//                    }
//                }
//
//            })

        }
    }

    override fun clickWx() {

    }

    override fun clickWb() {

    }

    override fun clickQQ() {


    }

    override fun onCleared() {
        super.onCleared()
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
    }

}