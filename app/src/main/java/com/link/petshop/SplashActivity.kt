package com.link.petshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.link.librarycomponent.ServiceFactory
import com.link.librarycomponent.router.RouterConstant

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ServiceFactory.getInstance().updateService!!.startUpdate(this)
        ARouter.getInstance().build(RouterConstant.SPLASH).navigation()
        finish()
    }

}