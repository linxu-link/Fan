package com.link.fan.app.splash

import android.Manifest
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarycomponent.router.RouterConstant
import com.link.librarymodule.base.BaseActivity
import com.link.librarymodule.utils.AppManager
import com.link.librarymodule.utils.ToastUtils
import com.link.librarymodule.utils.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(Consumer {
                    if (it) {
                        ARouter.getInstance().build(RouterConstant.SPLASH).navigation()
                        finish()
                    } else {
                        ToastUtils.showLong("您已禁止此应用读取存储设备")
                        AppManager.instance.AppExit()
                    }
                })
    }

}