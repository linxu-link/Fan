package com.link.fan.data.repository.source.net

import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.QueryListener
import com.link.fan.R
import com.link.fan.data.bean.BaseEntity
import com.link.fan.data.bean.MenuResult
import com.link.librarymodule.utils.RxCountDown
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.utils.Utils
import io.reactivex.Observable
import java.util.concurrent.CountDownLatch

/**
 * copyright:TS
 * author:wujia
 * create:2019-11-05-18:56
 * email:wujia0916@thundersoft.com
 * description:  网络数据处理类。
 * 如果有需要，可以在这里对网络来源的数据做简单的处理
 */
class NetServiceImpl constructor(private val service: RetrofitHttpService) : INetService {

    override fun homeBanner(): Observable<BaseEntity<MenuResult>> {
        return service.homeBanner()
    }

    override fun today(): Observable<BaseEntity<MenuResult>> {
        return service.today()
    }

    override fun lastMenu(): Observable<BaseEntity<MenuResult>> {
        return service.lastMenu()
    }

    override fun home(): Observable<BaseEntity<MenuResult>> {
        return service.home()
    }

    /**
     * 登录，将第三方SDK的异步操作，使用CountDownLatch修改为同步操作
     */
    override fun login(phone: String, smsCode: String): BmobException? {
        val countDownLatch = CountDownLatch(1)
        var bmobException: BmobException? = null
        BmobUser.loginBySMSCode(phone, smsCode, object : LogInListener<BmobUser>() {

            override fun done(user: BmobUser?, exception: BmobException?) {
                bmobException = exception
                countDownLatch.countDown()
            }

        })
        countDownLatch.await()
        return bmobException
    }

    /**
     * 获取短信验证码，将第三方SDK的异步操作，使用CountDownLatch修改为同步操作
     */
    override fun getSmsCode(phone: String): BmobException? {

        val countDownLatch = CountDownLatch(1)
        var bmobException: BmobException? = null
        BmobSMS.requestSMSCode(phone, Utils.getContext().resources.getString(R.string.application_name), object : QueryListener<Int>() {
            override fun done(smsId: Int?, e: BmobException?) {
                bmobException = e
                countDownLatch.countDown()
            }
        })
        countDownLatch.await()
        return bmobException
    }

    companion object {
        @Volatile
        private var sInstance: NetServiceImpl? = null

        fun getInstance(service: RetrofitHttpService) =
                sInstance ?: synchronized(this) {
                    sInstance ?: NetServiceImpl(service).also {
                        sInstance = it
                    }
                }
    }
}