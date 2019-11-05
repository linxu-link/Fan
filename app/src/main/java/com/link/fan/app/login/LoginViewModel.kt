package com.link.fan.app.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
 * description:
 */
class LoginViewModel constructor(private var repository: AppRepository) : ViewModel() {

    var phoneNumber = MutableLiveData<String>("")
    var phoneCode = MutableLiveData<String>("")
    var sendBtnEnable = MutableLiveData<Boolean>(true)
    var codeBtnText = MutableLiveData<String>("获取验证码")

    fun login() {

        if (phoneCode.value.isNullOrEmpty()) {
            return
        }

        if (phoneNumber.value.isNullOrEmpty()) {
            return
        }

        repository.login(phoneNumber.value!!, phoneCode.value!!)

    }

    private var mDisposable: Disposable? = null

    fun clickSmsCode() {
        if (sendBtnEnable.value!!) {

            if (phoneNumber.value.isNullOrEmpty()) {
                ToastUtils.showShort("请输入手机号码")
                return
            }

            repository.getSmsCode(phoneNumber.value!!)

            mDisposable = RxCountDown.countdown(60)
                    .subscribe(Consumer {
                        sendBtnEnable.value = (it == 0)
                        if (it == 0) {
                            codeBtnText.value = "获取验证码"
                        } else {
                            codeBtnText.value = "已发送剩余${it}秒"
                        }
                    })
        }
    }

    fun clickWx() {

    }

    fun clickWb() {

    }

    fun clickQQ() {


    }

    override fun onCleared() {
        super.onCleared()
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
    }

}